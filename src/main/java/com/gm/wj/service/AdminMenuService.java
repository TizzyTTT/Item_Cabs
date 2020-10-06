package com.gm.wj.service;

import com.gm.wj.dao.AdminMenuDAO;
import com.gm.wj.entity.AdminMenu;
import com.gm.wj.entity.AdminRoleMenu;
import com.gm.wj.entity.AdminUserRole;
import com.gm.wj.entity.User;
import org.apache.shiro.SecurityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evan
 * @date 2019/10
 */
@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDAO adminMenuDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDAO.findAllByParentId(parentId);
    }

    public List<AdminMenu> getMenusByCurrentUser() {
        // Get current user in DB.
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);

        // Get roles' ids of current user.
        List<Integer> rids = adminUserRoleService.listAllByUid(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
//        System.out.println("roles:");
//        for(int i = 0;i < rids.size();++i)
//            System.out.print(rids.get(i)+" ");
//        System.out.println();

        // Get menu items of these roles.
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
//        System.out.println("menus:");
//        for(int i = 0;i < menuIds.size(); ++i)
//            System.out.print(menuIds.get(i)+" ");
//        System.out.println();

        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());
//        System.out.println("menus detail:");
//        for(int i = 0;i < menus.size(); ++i)
//            System.out.print(menus.get(i).toString()+" ");
//        System.out.println();

        // Adjust the structure of the menu.
//        handleMenus(menus);
        handleMenus2(menus);
//        for(int i = 0;i < menus.size(); ++i)
//            System.out.print(menus.get(i).toString()+" ");
//        System.out.println();
        return menus;
    }

    public List<AdminMenu> getMenusByCurrentUser2() {
        // Get current user in DB.
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("current user ："+username);
        User user = userService.findByUsername(username);

        // Get roles' ids of current user.
        List<Integer> rids = adminUserRoleService.listAllByUid(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());

        // Get menu items of these roles.
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // Adjust the structure of the menu.
        handleMenus(menus);
        return menus;
    }


    public List<AdminMenu> getMenusByRoleId(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());

        System.out.println("rid" + rid);
        for (int i = 0; i < menuIds.size(); ++i) {
            System.out.println(menuIds.get(i));
        }

        Integer[] array = (Integer[]) menuIds.toArray(new Integer[menuIds.size()]);
//        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);
        List<AdminMenu> menus = adminMenuDAO.findByIdIn(array);
        handleMenus(menus);
//        handleMenus2(menus);
        return menus;
    }

    public List<Integer> getMenusByRoleId2(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        Integer[] array = (Integer[]) menuIds.toArray(new Integer[menuIds.size()]);
        List<AdminMenu> menus = adminMenuDAO.findByIdIn(array);
        return menus.stream().map(AdminMenu::getId).collect(Collectors.toList());
//        return menus;
    }

    /**
     * Adjust the Structure of the menu.
     *
     * @param menus Menu items list without structure
     */
    public void handleMenus(List<AdminMenu> menus) {
        menus.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }

    public void handleMenus2(List<AdminMenu> menus) {
        List<AdminMenu> list = new ArrayList<>();
        List<AdminMenu> list2 = new ArrayList<>();

        menus.forEach(m -> {
            if(m.getParentId() == 0){
                list.add(m);
            }else {
                list2.add(m);
            }
        });

        list2.forEach(child -> {
            list.forEach(parent -> {
                if(child.getParentId() == parent.getId()){
                    List<AdminMenu> temp = parent.getChildren();
                    if (temp == null)temp = new ArrayList<>();
                    temp.add(child);
                    parent.setChildren(temp);
                }
            });
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }
}

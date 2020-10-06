package com.gm.wj.service;

import com.gm.wj.New_All.utils.MaxUse;
import com.gm.wj.dao.AdminRoleDAO;
import com.gm.wj.entity.*;
import com.mysql.cj.protocol.Security;
import org.apache.shiro.SecurityUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminMenuService adminMenuService;

    public List<AdminRole> listWithPermsAndMenus() {

        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);
        Organization organization = user.getOrganization();
        List<AdminRole> roles = adminRoleDAO.findAllByOrganization(organization);
//        List<AdminRole> roles = adminRoleDAO.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roles) {
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);

            System.out.println(role.getNameZh() );
            if(menus.size() > 0) {
                for(int i = 0;i < menus.size(); ++i){
                    System.out.println(menus.get(i).getName()+' '+menus.get(i).getChildren().size());
                }
            }else {
                System.out.println( " none");
            }
        }
        return roles;
    }

    public List<NN> listMenus(){
        List<NN> list = new ArrayList<>();
        List<AdminRole> roles = adminRoleDAO.findAll();
        List<Integer> menusIds;
        for (AdminRole role : roles) {
            // for each role find the menu ids
            menusIds = adminMenuService.getMenusByRoleId2(role.getId());
            Integer[] array = (Integer[]) menusIds.toArray(new Integer[menusIds.size()]);
            //pack it
            NN node  = new NN();
            node.setId(role.getId());
            node.setArray(array);
            list.add(node);
        }
        return list;
    }


    public List<AdminRole> findAll() {
        return adminRoleDAO.findAll();
    }


    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    public List<AdminRole> listRolesByUser(String username) {
        int uid = userService.findByUsername(username).getId();
        List<Integer> rids = adminUserRoleService.listAllByUid(uid)
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDAO.findAllById(rids);
    }

    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return adminRoleDAO.save(roleInDB);
    }

    public void editRole(@RequestBody AdminRole role) {

        //单位隔离
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);
        Organization org = user.getOrganization();
        role.setOrganization(org);

        adminRoleDAO.save(role);
        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }

    class NN{
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Integer[] getArray() {
            return array;
        }

        public void setArray(Integer[] array) {
            this.array = array;
        }

        int id;
        Integer [] array;
    }
}

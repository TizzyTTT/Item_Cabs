package com.gm.wj.service;

import com.gm.wj.dao.AdminMenuDAO;
import com.gm.wj.dao.AdminRoleMenuDAO;
import com.gm.wj.entity.AdminMenu;
import com.gm.wj.entity.AdminRole;
import com.gm.wj.entity.AdminRoleMenu;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRoleMenuService {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    @Autowired
    AdminMenuDAO adminMenuDAO;

    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDAO.findAllByRid(rid);
    }

    public List<AdminRoleMenu> findAllByRid(List<Integer> rids) {
        return adminRoleMenuDAO.findAllByRid(rids);
    }

    public void save(AdminRoleMenu rm) {
        adminRoleMenuDAO.save(rm);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        adminRoleMenuDAO.deleteAllByRid(rid);
        List<AdminRoleMenu> rms = new ArrayList<>();

        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for(Integer mid : menusIds.get("menusIds")){
            tmp.add(mid);
        }
        Integer[] array = (Integer[])tmp.toArray(new Integer[tmp.size()]);

        List<AdminMenu> list = adminMenuDAO.findByIdIn(array);
        HashSet<Integer> set = new HashSet<Integer>();
        for (AdminMenu adminMenu : list) {
            int pid = adminMenu.getParentId();

            if(!set.contains(pid)){
                set.add(pid);
            }else continue;

            AdminRoleMenu rm = new AdminRoleMenu();
            rm.setRid(rid);
            rm.setMid(pid);
            rms.add(rm);
            System.out.println("pid : "+pid);
        }
        for (Integer mid : menusIds.get("menusIds")) {
            AdminRoleMenu rm = new AdminRoleMenu();
            rm.setRid(rid);
            rm.setMid(mid);
            rms.add(rm);
            System.out.println("mid : "+mid);
        }

        adminRoleMenuDAO.saveAll(rms);
    }
}

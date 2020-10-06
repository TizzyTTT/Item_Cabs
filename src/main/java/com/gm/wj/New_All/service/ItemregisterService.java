package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.item.ItemregisterDAO;
import com.gm.wj.New_All.entity.Itemregister;
import com.gm.wj.entity.Organization;
import com.gm.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemregisterService {

    @Autowired
    ItemregisterDAO itemregisterDAO;

    @Autowired
    UserService userService;

    public List<Itemregister> list(){
        List<Itemregister> list = itemregisterDAO.findAll();
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        list.removeIf(m -> m.getItem().getItemcategory().getOrganization().getId() != org.getId());
        return list;
    }

    public int add(Itemregister itemregister){
        try {
            itemregisterDAO.save(itemregister);
        }catch (Exception e){
            return -1;
        }
        return 0;
    }
}

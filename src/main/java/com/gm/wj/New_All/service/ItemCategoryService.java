package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.item.ItemCategoryDAO;
import com.gm.wj.New_All.entity.Itemcategory;
import com.gm.wj.entity.Organization;
import com.gm.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryService {

    @Autowired
    ItemCategoryDAO itemCategoryDAO;

    @Autowired
    UserService userService;

    public List<Itemcategory> list(){
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        List<Itemcategory> list = itemCategoryDAO.findAllByOrganization(org);

//        List<Itemcategory> list = itemCategoryDAO.findAll();
//        list.removeIf(m -> m.getOrganization().getId() != OrgUtils.getOrganization().getId());
        return list;
    }

    public Itemcategory getCategoryById(int cid){
       return itemCategoryDAO.findById(cid);
    }

}

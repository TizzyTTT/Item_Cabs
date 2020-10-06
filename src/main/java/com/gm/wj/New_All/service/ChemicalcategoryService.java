package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.chemcials.ChemicalscategoryDAO;
import com.gm.wj.New_All.entity.Chemicalcategory;
import com.gm.wj.entity.Organization;
import com.gm.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.shiro.SecurityUtils;

import java.util.List;

@Service
public class ChemicalcategoryService {

    @Autowired
    ChemicalscategoryDAO chemicalscategoryDAO;

    @Autowired
    UserService userService;

    public List<Chemicalcategory> getAllCategorys(){
        List<Chemicalcategory> list = chemicalscategoryDAO.findAll();
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        list.removeIf(m -> m.getOrganization().getId() != org.getId());

        return list;
    }

    public Chemicalcategory getCategoryById(int cid){ return chemicalscategoryDAO.findById(cid);}

}

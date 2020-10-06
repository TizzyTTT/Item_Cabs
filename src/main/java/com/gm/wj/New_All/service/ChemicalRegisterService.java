package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.chemcials.ChemicalsRegisterDAO;
import com.gm.wj.New_All.entity.Chemicalsregister;
import com.gm.wj.entity.Organization;
import com.gm.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChemicalRegisterService {

    @Autowired
    ChemicalsRegisterDAO chemicalRegisterDAO;

    @Autowired
    UserService userService;
    public List<Chemicalsregister> ListChemicalsRegister(){
        List<Chemicalsregister> list = chemicalRegisterDAO.findAll();
        String t_name = SecurityUtils.getSubject().getPrincipal().toString();
        Organization org = userService.findByUsername(t_name).getOrganization();
        list.removeIf(m -> m.getChemicals().getChemicalcategory().getOrganization().getId() != org.getId());

        return list;
    }

    public void batchAddReg(List<Chemicalsregister> list){
        chemicalRegisterDAO.saveAll(list);
    }

    public void addBatch(){

    }

}

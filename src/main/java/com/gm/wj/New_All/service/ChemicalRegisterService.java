package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.ChemicalsRegisterDAO;
import com.gm.wj.New_All.entity.Chemicalsregister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChemicalRegisterService {

    @Autowired
    ChemicalsRegisterDAO chemicalRegisterDAO;

    public List<Chemicalsregister> ListChemicalsRegister(){
        System.out.println("--------------");
        List<Chemicalsregister> obj = chemicalRegisterDAO.findAll();
        System.out.println("obj.size() "+obj.size());
        return obj;
    }

}

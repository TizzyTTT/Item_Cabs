package com.gm.wj.New_All.service;

import com.gm.wj.New_All.dao.ChemicalscategoryDAO;
import com.gm.wj.New_All.entity.Chemicalcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChemicalcategoryService {

    @Autowired
    ChemicalscategoryDAO chemicalscategoryDAO;

    public List<Chemicalcategory> getAllCategorys(){
        return chemicalscategoryDAO.findAll();
    }

    public Chemicalcategory getCategoryById(int cid){ return chemicalscategoryDAO.findById(cid);}

}

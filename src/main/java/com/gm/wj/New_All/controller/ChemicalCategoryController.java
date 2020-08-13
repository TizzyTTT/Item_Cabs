package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.service.ChemicalcategoryService;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChemicalCategoryController {

    @Autowired
    ChemicalcategoryService chemicalcategoryService;

    @GetMapping("/api/admin/chemicals/listallcategorys")
    public Result getCategorys(){
        return ResultFactory.buildSuccessResult(chemicalcategoryService.getAllCategorys());
    }

}

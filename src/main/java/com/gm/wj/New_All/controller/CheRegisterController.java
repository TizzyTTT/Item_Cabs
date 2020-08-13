package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.Chemicalsregister;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.New_All.service.ChemicalRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheRegisterController {

    @Autowired
    ChemicalRegisterService chemicalRegisterService;

    @GetMapping("/api/admin/chemicals/getcheregister")
    public Result getChemicalRegister(){
//        List<ChemicalsRegister> obj = null;
        List<Chemicalsregister> obj = chemicalRegisterService.ListChemicalsRegister();
        System.out.println(" getcheregister "+obj.size()+"size");
        return ResultFactory.buildSuccessResult(obj);
    }

//test

    @GetMapping("/api/admin/user/test")
    public Result listUsers2() {
        System.out.println("测试API");
        List<Chemicalsregister> obj = chemicalRegisterService.ListChemicalsRegister();
        System.out.println("number : "+obj.size());
        return ResultFactory.buildSuccessResult(obj);
    }



}

package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.Chemicalcategory;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.Register;
import com.gm.wj.New_All.service.ChemicalBasicService;
import com.gm.wj.New_All.service.ChemicalcategoryService;
import com.gm.wj.New_All.service.RegisterService;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    ChemicalBasicService chemicalBasicService;

    @Autowired
    ChemicalcategoryService chemicalcategoryService;

    @PutMapping("/api/admin/chemicals/addNewRegister/{chemicalid}/{totalweight}")
    public Result add(@PathVariable("chemicalid") int chemicalid, @PathVariable("totalweight") double totalweight){

        Chemicals chemicals = chemicalBasicService.findById(chemicalid);

        Register register = new Register();
        register.setChemicals(chemicals);
        register.setTotalweight(totalweight);
        register.setInweight(0);
        register.setRegDate(new Date());

        int status = registerService.add(register);
        if(status == -1){
            return ResultFactory.buildFailResult("保存登记信息失败");
        }else {
            return ResultFactory.buildSuccessResult("登记成功");
        }
    }

    @GetMapping("/api/admin/chemicals/listAllRegister")
    public Result list(){
        return ResultFactory.buildSuccessResult( registerService.List());
    }

    @GetMapping("/api/admin/chemicals/getSelect")
    public Result getSelect(){
//        Option option1 = new Option();
//        option1.setValue("A");
//        option1.setLabel("a");
//
//        Option option2 = new Option();
//        option2.setValue("B");
//        option2.setLabel("b");
//        List<Option> list = new ArrayList<>();
//        list.add(option1);
//        list.add(option2);
        System.out.println("------------------");
        return ResultFactory.buildSuccessResult(registerService.getSelect());
    }

    @GetMapping("api/admin/chemicals/searchRegister")
    public Result search(@RequestParam("keywords") String keywords,@RequestParam("categoryid") int cid){
        //查找类别
        Chemicalcategory chemicalcategory = chemicalcategoryService.getCategoryById(cid);
        //查找属于该类别的基础信息
        List<Chemicals> list = chemicalBasicService.findByChemicalcategory(chemicalcategory);

        //根据基础信息查询登记记录
        if(keywords.equals("")){
            System.out.println("list1.size() "+ list.size());
            return ResultFactory.buildSuccessResult(registerService.findByChemicalsIn(list));
        }else {
            //从list里面查
            System.out.println("list2.size() "+ list.size());
            List<Chemicals> list2 = new ArrayList<>();
            for(Chemicals c:list){
                if( StringUtils.containsIgnoreCase(c.getChemicalname(), keywords) || StringUtils.containsIgnoreCase(c.getChemicalno(), keywords) ){
                    list2.add(c);
                }
            }
            return ResultFactory.buildSuccessResult(registerService.findByChemicalsIn(list2));
        }
    }

}

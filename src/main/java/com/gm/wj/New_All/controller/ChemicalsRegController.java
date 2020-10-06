package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.Chemicalsregister;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.New_All.service.ChemicalRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"药品总入库接口"})
public class ChemicalsRegController {

    @Autowired
    ChemicalRegisterService chemicalRegisterService;

    @GetMapping("/api/admin/chemicals/getChemicalReg")
    @ApiOperation(value = "获取药品的全部库存信息",notes = "")
    public Result list(){
        List<Chemicalsregister> obj = chemicalRegisterService.ListChemicalsRegister();
        return ResultFactory.buildSuccessResult(obj);
    }

    @ApiOperation(value = "批量添加药品入库记录",notes = "")
    @PutMapping("/api/admin/chemicals/batchAddChemcialReg")
    public Result batchAdd(List<Chemicalsregister> list){
        chemicalRegisterService.batchAddReg(list);
        return ResultFactory.buildSuccessResultWithMessage("批量添加成功");
    }



}

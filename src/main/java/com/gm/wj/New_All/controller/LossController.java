package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.service.ChemicallossDetailService;
import com.gm.wj.New_All.service.ContainerlossDetailService;
import com.gm.wj.New_All.service.LossService;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags={"与日常损失相关的接口"})
public class LossController {


    @Autowired
    LossService lossService;

    @Autowired
    ContainerlossDetailService containerlossDetailService;

    @Autowired
    ChemicallossDetailService chemicallossDetailService;

    @GetMapping("/api/admin/record/getMaxLossChemical")
    @ApiOperation(value = "获取损失最多的药品")
    public Result getMaxLossChemical(){
        return ResultFactory.buildSuccessResult(chemicallossDetailService.getMaxLoss());
    }

    @GetMapping("/api/admin/record/getLossByChemicalid/{chemicalid}")
    @ApiOperation(value = "根据药品id寻找损失")
    public Result getLossByChemicalid(@PathVariable("chemicalid") int chemicalid){
        return ResultFactory.buildSuccessResult(chemicallossDetailService.listByChemicalid(chemicalid));
    }


}

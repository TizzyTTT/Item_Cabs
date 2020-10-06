package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.Chemicalcategory;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.service.ChemicalBasicService;
import com.gm.wj.New_All.service.ChemicalcategoryService;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags={"药品基础信息查询，更新，添加"})
public class ChemicalsController {

    @Autowired
    ChemicalBasicService chemicalBasicService;

    @Autowired
    ChemicalcategoryService chemicalcategoryService;

    @GetMapping("/api/admin/chemicals/listallchemicals")
    @ApiOperation(value = "陈列所有药品基础信息",notes = "")
    public Result listAllChemicals(){
        return ResultFactory.buildSuccessResult(chemicalBasicService.listAll());
    }


    @PostMapping("api/admin/chemicals/addchemcials")
    @ApiOperation(value = "添加新的药品信息",notes = "")
    public Result addchemcials(@RequestBody Chemicals chemicals){
        System.out.println(chemicals.toString());
        int status =  chemicalBasicService.add(chemicals);
        switch (status){
            case -1:
                return ResultFactory.buildFailResult("添加失败");
            case 1:
                return ResultFactory.buildSuccessResult("保存成功");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @PostMapping("api/admin/chemicals/updatechemicals")
    @ApiOperation(value = "药品基础信息修改",notes = "")
    public Result updatechemicals(@RequestBody Chemicals chemicals){
        System.out.println(chemicals.toString());
        int status =  chemicalBasicService.update(chemicals);
        switch (status){
            case -1:
                return ResultFactory.buildFailResult("更新失败");
            case 1:
                return ResultFactory.buildSuccessResult("更新成功");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @DeleteMapping("api/admin/chemicals/removechemcials/{chemicalno}")
    @ApiOperation(value = "删除药品基础信息",notes = "")
    public Result removechemcials(@PathVariable("chemicalno") String chemicalno){
        System.out.println("chemicalno "+chemicalno);
        int status = chemicalBasicService.remove(chemicalno);
        if (status == 1){
            return ResultFactory.buildSuccessResult("移除成功");
        }else {
            return ResultFactory.buildFailResult("移除失败");
        }
    }


    @GetMapping("api/admin/chemicals/searchchemicals")
    @ApiOperation(value = "根据药品编号或者名称修改药品信息",notes = "")
    public Result search(@RequestParam("keywords") String keywords,@RequestParam("categoryid") int cid){

        System.out.println("keywords:"+keywords+"\n"+"cid:"+cid);
        Chemicalcategory chemicalcategory = chemicalcategoryService.getCategoryById(cid);
        return ResultFactory.buildSuccessResult(chemicalBasicService.search(chemicalcategory,keywords));
    }

    @GetMapping("/api/admin/chemicals/listallcategorys")
    @ApiOperation(value = "陈列所有的药品类别",notes = "")
    public Result getCategorys(){
        return ResultFactory.buildSuccessResult(chemicalcategoryService.getAllCategorys());
    }


}

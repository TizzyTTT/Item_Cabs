package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.CabItem;
import com.gm.wj.New_All.entity.Cabinets;
import com.gm.wj.New_All.service.CabService;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
获取所有柜子概要信息
获取某个详细信息（药品存放，出入记录）

添加柜子
编辑柜信息

更新柜子物品存放情况

 */

@RestController
public class CabinetsController {

    @Autowired
    CabService cabService;

    @ApiOperation(value="获取所有柜子概要信息", notes="信息包括id,编号,位置,使用情况等")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/api/admin/cab/list")
    public Result list(){
        return ResultFactory.buildSuccessResult(cabService.list());
    }

    @ApiOperation(value = "获取某个柜子详细信息", notes = "信息包括药品存放情况")
    @GetMapping("/api/admin/cab/getOne/{cabid}")
    public Result getOne(@PathVariable("cabid") int cabid){
        return ResultFactory.buildSuccessResult(cabService.getOne(cabid));
    }

    @ApiOperation(value = "编辑或者添加柜子信息", notes = "柜子的编号,存放地址等信息")
    @PostMapping("/api/admin/cab/editCab")
    public Result edit(@RequestBody Cabinets cabinets){
        cabService.editOrAdd(cabinets);
        return ResultFactory.buildSuccessResult("成功");
    }

    @ApiOperation(value = "更新柜内物品信息", notes = "输入....")
    @PostMapping("/api/admin/cab/updateCabItem")
    public Result edit(@RequestBody List<CabItem> list){
        cabService.updateCabItem(list);
        return ResultFactory.buildSuccessResult("成功");
    }



}

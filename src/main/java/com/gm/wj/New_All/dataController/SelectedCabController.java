package com.gm.wj.New_All.dataController;

import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "跳转到具体某个柜子时候的数据大屏接口")
public class SelectedCabController {

    //header表格s
    @GetMapping("/api/admin/selectedcabpanel/getCabErrorList")
    @ApiOperation(value = "获取某个柜子概要信息，展示在页面header部分")
    public Result getCabErrorList(){
        return ResultFactory.buildSuccessResultWithMessage("获取未解决的柜体异常情况发生列表");
    }

    //左侧表格s
    @GetMapping("/api/admin/selectedcabpanel/usedMostDrugs")
    @ApiOperation(value = "使用最多的药品")
    public Result usedMostDrugs(){
        return ResultFactory.buildSuccessResultWithMessage("使用最多的药品");
    }

    @GetMapping("/api/admin/selectedcabpanel/userOperations")
    @ApiOperation(value = "这个柜子最近的用户操作记录")
    public Result userOperations(){
        return ResultFactory.buildSuccessResultWithMessage("这个柜子最近的用户操作记录");
    }

    @GetMapping("/api/admin/selectedcabpanel/drugsUseAndLoss")
    @ApiOperation(value = "药品使用与损失情况")
    public Result drugsUseAndLoss(){ return ResultFactory.buildSuccessResultWithMessage("药品使用与损失情况"); }

    //右侧表格
    @GetMapping("/api/admin/selectedcabpanel/cabEnvirParam")
    @ApiOperation(value = "展示柜子内部的温度，湿度，气体浓度")
    public Result getcabEnvirParams(){ return ResultFactory.buildSuccessResultWithMessage("展示柜子内部的温度，湿度，气体浓度"); }

    @GetMapping("/api/admin/selectedcabpanel/cabHardParam")
    @ApiOperation(value = "硬件运行情况，RFID传感器，重力传感器，锁控端，电量等等的数据展示")
    public Result getcabHardParams(){ return ResultFactory.buildSuccessResultWithMessage("硬件运行情况，RFID传感器，重力传感器，锁控端，电量等等的数据展示"); }

    @GetMapping("/api/admin/selectedcabpanel/batteryCostList")
    @ApiOperation(value = "耗电量信息列表")
    public Result getBatteryCost(){ return ResultFactory.buildSuccessResultWithMessage("耗电量信息列表"); }

    @GetMapping("/api/admin/selectedcabpanel/frequentErrorList")
    @ApiOperation(value = "常见故障排行榜信息列表")
    public Result frequentErrorList(){ return ResultFactory.buildSuccessResultWithMessage("常见故障排行榜信息列表"); }




}

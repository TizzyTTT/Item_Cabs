package com.gm.wj.New_All.dataController;

import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"该单位药品概要信息数据大屏的接口"})
public class AllChemicalsPanelController {

    //左侧表
    //药品使用量，损耗量对比折线图，近7周以来或者近7个月
    @GetMapping("/api/admin/alldrugspanel/getDrugsVersus")
    @ApiOperation(value = "药品使用量，损耗量对比折线图，近7周以来或者近7个月")
    public Result getDrugsVersus(){return ResultFactory.buildSuccessResultWithMessage("近7周以来或者近7个月");}

    //中间表格
    //维修记录
    @GetMapping("/api/admin/alldrugspanel/FrequentUsedDrugsList")
    @ApiOperation(value = "最常使用的药品名称列表")
    public Result FrequentUsedDrugs(){return ResultFactory.buildSuccessResultWithMessage("最常使用的药品名称列表");}

    //药品出入库情况
    @GetMapping("/api/admin/alldrugspanel/DrugsIOStatus")
    @ApiOperation(value = "药品出入库情况,实时变化，滚动屏幕")
    public Result DrugsIOStatus(){return ResultFactory.buildSuccessResultWithMessage("药品出入库情况,实时变化，滚动屏幕");}

    //右侧表格
    //异常情况清单：用户危柜拿走了某些药品或者损失了xxx
    @GetMapping("/api/admin/alldrugspanel/AbnormalList")
    @ApiOperation(value = "异常情况清单,用户危柜拿走了某些药品或者损失了xxx")
    public Result MaintenanceRecords(){return ResultFactory.buildSuccessResultWithMessage("异常情况清单：用户危柜拿走了某些药品或者损失了xxx");}

    //下面
    @GetMapping("/api/admin/alldrugspanel/SurplusLeast")
    @ApiOperation(value = "全体：陈列出剩余最少的药品，提示用户及时入库")
    public Result SurplusLeast(){return ResultFactory.buildSuccessResultWithMessage("陈列出剩余最少的药品，提示用户及时入库");}





}

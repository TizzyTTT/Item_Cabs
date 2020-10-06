package com.gm.wj.New_All.controller;


import com.gm.wj.New_All.entity.Record;
import com.gm.wj.New_All.entity.RecordDetail;
import com.gm.wj.New_All.service.RecordDetailService;
import com.gm.wj.New_All.service.RecordService;
import com.gm.wj.New_All.utils.*;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
添加一条记录
查看某个柜子的出入记录 时间 物品 条件查询
查看
 */

@RestController
@Api(tags = {"与用户操作记录有关"})
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    RecordDetailService recordDetailService;

    @PostMapping("/api/admin/record/listAllByCondition")
    @ApiOperation(value = "根据条件查询用户操作记录", notes = "")
    public Result listAllByCondition(@RequestBody QueryForRecord queryForRecord){
        if(queryForRecord == null){
            return ResultFactory.buildFailResult("查询失败");
        }
        return ResultFactory.buildSuccessResult(recordService.query(queryForRecord));
    }

    @GetMapping("/api/admin/record/listDetailByRecordId/{rid}")
    @ApiOperation(value = "根据记录id查询，此次操作下的详细情况", notes = "")
    public Result listRecordByQuery(@PathVariable("rid") int rid){
        if(rid <= -1) return ResultFactory.buildFailResult("柜id非法");
        return ResultFactory.buildSuccessResult(recordDetailService.listByRecordid(rid));
    }

    @PostMapping("/api/admin/record/listDetailByConditions")
    @ApiOperation(value = "条件查询,二级目录", notes = "")
    public Result listDetailByConditions(@RequestBody QueryForDetail queryForDetail){
        if(queryForDetail == null){
            return ResultFactory.buildFailResult("查询失败");
        }
        return ResultFactory.buildSuccessResult(recordDetailService.query(queryForDetail));
    }

    // 从柜子端过来的请求
    @PostMapping("/api/admin/record/Record")
    @ApiOperation(value = "添加一条操作记录", notes = "")
    public Result Record(@RequestBody Record2 record){
        System.out.println(record.toString());
        //把record2 转换成record类型
        recordService.convertAndInsertRecord(record);
        return ResultFactory.buildSuccessResult("添加成功");
    }

    // 从柜子端过来的请求
    @DeleteMapping("/api/admin/record/delete/{rid}")
    @ApiOperation(value = "删除一条操作记录", notes = "")
    public Result Record(@PathVariable("rid") int rid){
        //把record2 转换成record类型
        recordService.deleteRecordCascade(rid);
        return ResultFactory.buildSuccessResult("删除成功");
    }

    // 从柜子端过来的请求
    @GetMapping("/api/admin/record/getRecordByChemicalid/{chemicalid}")
    @ApiOperation(value = "获取某个药品的操作记录", notes = "")
    public Result getRecordByChemicalid(@PathVariable("chemicalid") int cid){
        return ResultFactory.buildSuccessResult(recordDetailService.listByChemicalid(cid));
    }

    @ApiOperation(value = "获取近期使用最多的药品", notes = "")
    @GetMapping("/api/admin/record/getMaxUse")
    public Result getMaxUse(){
        return ResultFactory.buildSuccessResult(recordDetailService.getMaxUse());
    }


//    @ApiOperation(value = "根据药品id寻找使用记录", notes = "")
//    @GetMapping("/api/admin/record/getUseByChemicalid/{chemicalid}")
//    public Result getUseByChemicalid(@PathVariable("chemicalid") int cid){
//        return ResultFactory.buildSuccessResult(recordDetailService.listByChemicalid(cid));
//    }


}

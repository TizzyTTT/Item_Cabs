package com.gm.wj.New_All.controller;


import com.gm.wj.New_All.entity.Record;
import com.gm.wj.New_All.entity.RecordDetail;
import com.gm.wj.New_All.service.RecordDetailService;
import com.gm.wj.New_All.service.RecordService;
import com.gm.wj.New_All.utils.QueryForDetail;
import com.gm.wj.New_All.utils.QueryForRecord;
import com.gm.wj.New_All.utils.Record2;
import com.gm.wj.New_All.utils.RecordDetail2;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
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
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    RecordDetailService recordDetailService;

    @PostMapping("/api/admin/record/listAllByCondition")
    @ApiOperation(value = "根据条件查询,一级目录", notes = "日期,操作人")
    public Result listAllByCondition(@RequestBody QueryForRecord queryForRecord){
        if(queryForRecord == null){
            return ResultFactory.buildFailResult("查询失败");
        }
        return ResultFactory.buildSuccessResult(recordService.query(queryForRecord));
    }

    @GetMapping("/api/admin/record/listDetailByRecordId/{rid}")
    @ApiOperation(value = "根据记录id查询,二级目录", notes = "输入记录id")
    public Result listRecordByQuery(@PathVariable("rid") int rid){
        if(rid <= -1) return ResultFactory.buildFailResult("柜id非法");
        return ResultFactory.buildSuccessResult(recordDetailService.listByRecordid(rid));
    }

    @PostMapping("/api/admin/record/listDetailByConditions")
    @ApiOperation(value = "条件查询,二级目录", notes = "操作类型,药品编号或名称")
    public Result listDetailByConditions(@RequestBody QueryForDetail queryForDetail){
        if(queryForDetail == null){
            return ResultFactory.buildFailResult("查询失败");
        }
        return ResultFactory.buildSuccessResult(recordDetailService);
    }


    // 从柜子端过来的请求
    @PostMapping("/api/admin/record/Record")
    public Result Record(@RequestBody Record2 record){
        System.out.println(record.toString());
        //把record2 转换成record类型
        recordService.convertAndInsertRecord(record);


        return ResultFactory.buildSuccessResult("添加成功");
    }

    //test
    // 从柜子端过来的请求
    @DeleteMapping("/api/admin/record/delete/{rid}")
    public Result Record(@PathVariable("rid") int rid){
        //把record2 转换成record类型
        recordService.deleteRecordCascade(rid);
        return ResultFactory.buildSuccessResult("删除成功");
    }

}

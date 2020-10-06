package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.service.CabPrivilegeService;
import com.gm.wj.New_All.utils.CabUser2;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"柜子使用权限相关"})
@RestController
public class CabPrivilegeController {

    @Autowired
    CabPrivilegeService cabPrivilegeService;

    @ApiOperation(value = "给用户配置柜子的使用权")
    @PostMapping("/api/admin/cabprivilege/addCabPrivilege2User")
    public Result addCabPrivilege2User(@RequestBody CabUser2 cabUser2){
        cabPrivilegeService.saveCabPrivilege(cabUser2);
        return ResultFactory.buildSuccessResultWithMessage("保存成功");
    }

    @ApiOperation(value = "陈列某个柜子的全部权限信息")
    @GetMapping("/api/admin/cabprivilege/list/{cid}")
    public Result list(@PathVariable("cid") int cid){
        return ResultFactory.buildSuccessResult(cabPrivilegeService.list(cid));
    }



}

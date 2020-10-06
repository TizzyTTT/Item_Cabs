package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.CabItem;
import com.gm.wj.New_All.entity.Cabinets;
import com.gm.wj.New_All.service.CabService;
import com.gm.wj.New_All.utils.SimpleUser;
import com.gm.wj.entity.User;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
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

@Api(tags={"与柜子基本信息相关的接口"})
@RestController
public class CabinetsController {

    @Autowired
    CabService cabService;

    @Autowired
    UserService userService;

    @ApiOperation(value="获取所有柜子的概要信息")
    @GetMapping("/api/admin/cab/list")
    public Result list(){
        return ResultFactory.buildSuccessResult(cabService.list());
    }

    @ApiOperation(value = "获取某个柜子详细信息")
    @ApiImplicitParam(name = "cabid", value = "柜子ID", required = true, dataType = "int")
    @GetMapping("/api/admin/cab/getOne/{cabid}")
    public Result getOne(@PathVariable("cabid") int cabid){
        return ResultFactory.buildSuccessResult(cabService.getOne(cabid));
    }

    @ApiOperation(value = "编辑或者添加柜子信息", notes = "如果id为空，新增柜子，如果id不为空，则修改信息，若id不存在，返回错误信息")

    @PostMapping("/api/admin/cab/editCab")
    public Result edit(@RequestBody Cabinets cabinets){
        int status = cabService.editOrAdd(cabinets);

        switch (status){
            case 0://成功
                return ResultFactory.buildSuccessResultWithMessage("成功");
            case -1:
                return ResultFactory.buildFailResult("失败");
            default:
                return ResultFactory.buildFailResult("失败");
        }
    }

    @ApiOperation(value = "更新柜内物品信息", notes = "输入....")
    @PostMapping("/api/admin/cab/updateCabItem")
    public Result edit(@RequestBody List<CabItem> list){
        int status = cabService.updateCabItem(list);
        switch (status){
            case -1:
                return ResultFactory.buildFailResult("药品id未找到或其他异常");
            case 0:
                return ResultFactory.buildSuccessResult("成功");
        }
        return ResultFactory.buildSuccessResult("成功");
    }

    @ApiOperation(value = "获取某用户可以操作的柜子列表")
    @GetMapping("/api/admin/cab/listforuser")
    public Result listforuser(){
        System.out.println("enter the list for user");
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("username "+username);
        User user = userService.findByUsername(username);

        return ResultFactory.buildSuccessResult(cabService.listforuser(new SimpleUser(user)));
    }

    @GetMapping("/api/admin/cab/getChemicalDistribute/{chemicalid}")
    @ApiOperation(value = "根据药品id查询药品分布情况")
    public Result getChemicalDistribute(@PathVariable("chemicalid") int id){
        return ResultFactory.buildSuccessResult(cabService.getChemicalDistribute(id));
    }

}


/*
总结：
    swagger2是不是很好用了，上面只演示了觉得用得上的注解，当然它还有一系列的注解帮助我们对接口和入参进行详细说明比如：
    @ApiImplicitParams：用在请求的方法上，表示一组参数说明
    @ApiImplicitParam：用在@ApiImplicitParams注解中，代表某一个具体参数，用于单个参数的各个信息说明
    @ApiResponses：用在请求的方法上，表示一组响应
    @ApiResponse：用在@ApiResponses中，表示响应的某个具体参数，用于对具体参数的各个方面进行说明
    @ApiModel：用于响应类上，表示一个返回响应数据的信息
    @ApiModelProperty：和@ApiModel搭配使用，用在响应类的属性上，描述响应类的属性信息
这些注解，可根据需要使用，通常只要参数定义的好，有具体的语义，我觉得不需要这么详细的备注，额外增加写注解的工作量。

//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
 */

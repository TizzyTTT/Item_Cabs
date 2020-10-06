package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.*;
import com.gm.wj.New_All.service.ItemCategoryService;
import com.gm.wj.New_All.service.ItemService;
import com.gm.wj.New_All.service.ItemregisterService;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Api(tags={"与仪器基础信息相关的接口"})
public class ItemController {

    @Autowired
    ItemCategoryService itemCategoryService;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemregisterService itemregisterService;

    @GetMapping("/api/admin/item/listallcategorys")
    @ApiOperation(value = "陈列所有的仪器类别",notes = "")
    public Result getCategorys(){
        return ResultFactory.buildSuccessResult(itemCategoryService.list());
    }

    @GetMapping("/api/admin/item/listallitems")
    @ApiOperation(value = "陈列所有仪器基础信息",notes = "")
    public Result listAllChemicals(){
        return ResultFactory.buildSuccessResult(itemService.list());
    }

    @PostMapping("/api/admin/item/itemupdate")
    @ApiOperation(value = "仪器基础信息修改",notes = "")
    public Result update(@RequestBody Item item){
        int status =  itemService.update(item);
        switch (status){
            case -1:
                return ResultFactory.buildFailResult("更新失败");
            case 1:
                return ResultFactory.buildSuccessResult("更新成功");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @PostMapping("/api/admin/item/addItem")
    @ApiOperation(value = "添加新的仪器信息",notes = "")
    public Result addItem(@RequestBody Item item){
        int status =  itemService.add(item);
        switch (status){
            case -1:
                return ResultFactory.buildFailResult("添加失败");
            case 1:
                return ResultFactory.buildSuccessResult("保存成功");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @GetMapping("/api/admin/item/listAllRegister")
    @ApiOperation(value = "陈列所有的登记仪器",notes = "")
    public Result listReg(){
        return ResultFactory.buildSuccessResult(itemregisterService.list());
    }

    @PostMapping("/api/admin/item/itemupdate2")
    @ApiOperation(value = "仪器信息修改",notes = "")
    public Result updateReg(@RequestBody Item item){
        int status = itemService.update(item);
        switch (status){
            case -1:
                return ResultFactory.buildFailResult("更新失败");
            case 1:
                return ResultFactory.buildSuccessResult("更新成功");
        }
        return ResultFactory.buildFailResult("未知错误");
    }


    @GetMapping("/api/admin/item/getSelect")
    @ApiOperation(value = "登记前需要选择哪个药品")
    public Result getSelect(){
        return ResultFactory.buildSuccessResult(itemService.getSelect());
    }


    @PutMapping("/api/admin/item/addNewRegister/{itemid}/{account}")
    @ApiOperation(value = "添加药品登记记录")
    public Result add(@PathVariable("itemid") int itemid, @PathVariable("account") int account){

        Item item = itemService.findById(itemid);
        Itemregister itemregister = new Itemregister();
        itemregister.setAccount(account);
        itemregister.setDate(new Date());
        itemregister.setItem(item);

        int status = itemregisterService.add(itemregister);
        if(status == -1){
            return ResultFactory.buildFailResult("保存登记信息失败");
        }else {
            return ResultFactory.buildSuccessResult("登记成功");
        }
    }

    @GetMapping("api/admin/item/searchRegister")
    @ApiOperation(value = "",notes = "")
    public Result search(@RequestParam("keywords") String keywords,@RequestParam("categoryid") int cid){
        Itemcategory itemcategory = itemCategoryService.getCategoryById(cid);
        return ResultFactory.buildSuccessResult(itemService.search(itemcategory,keywords));
    }



}

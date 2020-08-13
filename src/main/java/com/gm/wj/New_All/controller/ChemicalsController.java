package com.gm.wj.New_All.controller;

import com.gm.wj.New_All.entity.Chemicalcategory;
import com.gm.wj.New_All.entity.Chemicals;
import com.gm.wj.New_All.entity.Chemicalsregister;
import com.gm.wj.New_All.service.ChemicalBasicService;
import com.gm.wj.New_All.service.ChemicalcategoryService;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class ChemicalsController {

    @Autowired
    ChemicalBasicService chemicalBasicService;

    @Autowired
    ChemicalcategoryService chemicalcategoryService;

    @GetMapping("/api/admin/chemicals/listallchemicals")
    public Result listAllChemicals(){
        return ResultFactory.buildSuccessResult(chemicalBasicService.listAll());
    }

    @PostMapping("/api/admin/chemicals/chemicalschange")
    public Result chemicalsChange(@RequestBody Chemicals chemicals){
        System.out.println(chemicals.toString());
        chemicalBasicService.update(chemicals);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @PostMapping("api/admin/chemicals/addchemcials")
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
    public Result search(@RequestParam("keywords") String keywords,@RequestParam("categoryid") int cid){
        Chemicalcategory chemicalcategory = chemicalcategoryService.getCategoryById(cid);
        return ResultFactory.buildSuccessResult(chemicalBasicService.search(chemicalcategory,keywords));
    }

}

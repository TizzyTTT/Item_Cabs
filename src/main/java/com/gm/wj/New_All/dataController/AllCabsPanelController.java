package com.gm.wj.New_All.dataController;

import com.gm.wj.result.Result;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags={"该单位全部柜子数据大屏的接口"})
public class AllCabsPanelController {

    //左侧表格s
    @GetMapping("/api/admin/allcabspanel/getCabErrorList")
    @ApiOperation(value = "获取未解决的柜体异常情况发生列表")
    public Result getCabErrorList(){
        return ResultFactory.buildSuccessResultWithMessage("获取未解决的柜体异常情况发生列表");
    }

    @GetMapping("/api/admin/allcabspanel/getCabErrorTotal")
    @ApiOperation(value = "获取柜体异常情况累计统计情况")
    public Result getCabErrorTotal(){ return ResultFactory.buildSuccessResultWithMessage("获取柜体异常情况累计统计情况");}

    @GetMapping("/api/admin/allcabspanel/getAttendantInfo")
    @ApiOperation(value = "维修人员的联系方式")
    public Result getAttendantInfo(){return ResultFactory.buildSuccessResultWithMessage("维修人员的联系方式");}

    //中间表格
    @GetMapping("/api/admin/allcabspanel/getCabsFloorsAndBasicInfo")
    @ApiOperation(value = "获取楼层内柜子位置信息，以及柜子概要信息，位置，运行状态...")
    public Result getCabsFloorsAndBasicInfo(){return ResultFactory.buildSuccessResultWithMessage("获取楼层内柜子位置信息，以及柜子概要信息，位置，运行状态...");}

    //右侧表格s
    @GetMapping("/api/admin/allcabspanel/MonitorScreen")
    @ApiOperation(value = "获取监控画面")
    public Result MonitorScreen(){return ResultFactory.buildSuccessResultWithMessage("获取监控画面");}

    //维修记录
    @GetMapping("/api/admin/allcabspanel/MaintenanceRecords")
    @ApiOperation(value = "维修记录")
    public Result MaintenanceRecords(){return ResultFactory.buildSuccessResultWithMessage("维修记录");}



}

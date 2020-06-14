package com.school.acl.controller;



import com.school.acl.entity.pojo.EduSub;
import com.school.acl.service.EduSubService;
import com.school.common.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
@Api("订阅管理")
@RestController
@RequestMapping("/admin/acl/sub")
//@CrossOrigin
public class SubController {


    @Autowired
    private EduSubService eduSubService;

    //获取订阅的列表
    @PostMapping("pageSub/{current}/{limit}")
    public R pageSubCondition(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) EduSub query) {

        return eduSubService.pageSubCondition(current,limit,query);
    }

    @ApiOperation(value = "逻辑删除订阅")
    @DeleteMapping("{id}")
    public R removeSub(@ApiParam(name = "id", value = "订阅ID", required = true)
                        @PathVariable String id) {
        boolean flag = eduSubService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //获取未订阅的列表
    @PostMapping("pageNotSub")
    public R pageNotSubCondition(@RequestBody(required = false) EduSub query) {
        return eduSubService.pageNotSubCondition(query);
    }

   //订阅
    @GetMapping("addSub/{id}")
    public R addSub(@PathVariable String id) {
        return  eduSubService.addSub(id);
    }


}


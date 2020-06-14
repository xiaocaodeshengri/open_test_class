package com.school.acl.controller;


import com.school.acl.entity.pojo.User;
import com.school.acl.entity.vo.UserQuery;
import com.school.acl.service.UserService;
import com.school.common.response.R;
import com.school.common.security.security.DefaultPasswordEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cwh
 * @since 2020-06-10
 */
@Api("用户管理")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    /**
     *  获取用户列表
     * @param current  当前页
     * @param limit   每页显示条数
     * @return
     */
    @PostMapping("pageUser/{current}/{limit}")
    public R pageUserCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) UserQuery query) {
        return userService.pageUserCondition(current,limit,query);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("addUser")
    public R addTeacher(@RequestBody User user) {
        user.setPassword(new DefaultPasswordEncoder().encode("123456"));
        System.out.println(user.getPassword());
        System.out.println(new DefaultPasswordEncoder().encode("Admin"));
        boolean save = userService.save(user);
        userService.addMenu(user);
        return R.ok();

    }

    /**
     * 依据ID获取用户信息
     * @param id
     * @return
     */
    @GetMapping("getUser/{id}")
    public R getUser(@PathVariable String id) {
        User user = userService.getById(id);
        return R.ok().data("user",user);
    }

    /**
     *  编辑用户
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public R updateUser(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @ApiOperation(value = "逻辑删除用户")
    @DeleteMapping("{id}")
    public R removeUser(@ApiParam(name = "id", value = "用户ID", required = true)
                           @PathVariable String id) {
        boolean flag = userService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}


package com.school.acl.service;

import com.school.acl.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.acl.entity.vo.UserQuery;
import com.school.common.response.R;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author cwh
 * @since 2020-06-10
 */
public interface UserService extends IService<User> {
    // 从数据库中取出用户信息
    User selectByUsername(String username);
   //带条件查询用户信息
    R pageUserCondition(long current, long limit, UserQuery query);
    //添加菜单
    void addMenu(User user);
}

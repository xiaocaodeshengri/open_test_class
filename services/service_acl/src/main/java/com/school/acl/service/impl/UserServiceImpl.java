package com.school.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.acl.entity.pojo.User;
import com.school.acl.entity.vo.UserQuery;
import com.school.acl.mapper.UserMapper;
import com.school.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.common.response.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cwh
 * @since 2020-06-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public R pageUserCondition(long current, long limit, UserQuery query) {
        //创建page对象
        Page<User> pageTeacher = new Page<>(current,limit);
        //构建条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        String name = query.getUsername();
        Integer type = query.getType();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("username",name);
        }
        if(!StringUtils.isEmpty(type)) {
            wrapper.eq("type",type);
        }
        //排除admin用户
        wrapper.ne ("type", 0);
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        baseMapper.selectMapsPage(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<User> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
}

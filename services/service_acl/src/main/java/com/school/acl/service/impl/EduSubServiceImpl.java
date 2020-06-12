package com.school.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.acl.entity.pojo.EduCourse;
import com.school.acl.entity.pojo.EduSub;
import com.school.acl.mapper.EduSubMapper;
import com.school.acl.service.EduCourseService;
import com.school.acl.service.EduSubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.common.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
@Service
public class EduSubServiceImpl extends ServiceImpl<EduSubMapper, EduSub> implements EduSubService {

    @Autowired
    private EduCourseService courseService;

    @Override
    public R pageSubCondition(long current, long limit, EduSub query) {
        //创建page对象
        Page<EduSub> pageTeacher = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduSub> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        String name = query.getCourseName();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("course_name",name);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        baseMapper.selectMapsPage(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduSub> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @Override
    public R pageNotSubCondition( EduSub query) {
         //查找所有的课程列表
        List<EduCourse> list = courseService.list(null);
         //获取用户订阅的课程
        //TODO
        List<EduSub> eduSubs = baseMapper.selectList(null);
        List<EduCourse> in =new  ArrayList<>();
        for (EduCourse item :list) {
            for (EduSub sub:eduSubs) {
                if (sub.getCourseId().equals(item.getId())) {
                    in.add(item);
                }
            }
        }
        list.removeAll(in);
        return  R.ok().data("rows",list);
    }

    @Override
    public R addSub(String id) {
        //更新订阅的数量
        EduCourse byId = courseService.getById(id);
        byId.setBuyCount(byId.getBuyCount() + 1);
        courseService.updateById(byId);

        //添加订阅信息
        //TODO
        EduSub sub =new EduSub();
        sub.setCourseId(byId.getId());
        sub.setCourseName(byId.getTitle());

        baseMapper.insert(sub);
        return R.ok();

    }
}

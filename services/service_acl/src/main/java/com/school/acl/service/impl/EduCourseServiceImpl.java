package com.school.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.acl.entity.pojo.EduCourse;
import com.school.acl.entity.vo.CourseInfoVo;
import com.school.acl.entity.vo.CourseQuery;
import com.school.acl.mapper.EduCourseMapper;
import com.school.acl.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.common.exception.MyException;
import com.school.common.response.R;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Override
    public R pageCourseCondition(long current, long limit, CourseQuery query) {
        //创建page对象
        Page<EduCourse> page = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        String title = query.getTitle();
        String status = query.getStatus();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        baseMapper.selectMapsPage(page,wrapper);

        long total = page.getTotal();//总记录数
        List<EduCourse> records = page.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        //TODO
        eduCourse.setCover("https://bulid01.oss-cn-beijing.aliyuncs.com/1.jpg");
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0) {
            //添加失败
            throw new MyException(20001,"添加课程信息失败");
        }
        //获取添加之后课程id
        String cid = eduCourse.getId();
        return cid;
    }

    //根据课程id查询课程确认信息
    @Override
    public  EduCourse  publishCourseInfo(String id) {
        //调用mapper
        EduCourse publishCourseInfo = baseMapper.selectById(id);
        return publishCourseInfo;
    }


}

package com.school.acl.service;

import com.school.acl.entity.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.acl.entity.vo.CourseInfoVo;
import com.school.acl.entity.vo.CourseQuery;
import com.school.common.response.R;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
public interface EduCourseService extends IService<EduCourse> {

    R pageCourseCondition(long current, long limit, CourseQuery query);

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    EduCourse publishCourseInfo(String id);
}

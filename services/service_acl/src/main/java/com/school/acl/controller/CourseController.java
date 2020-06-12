package com.school.acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.acl.entity.pojo.EduChapter;
import com.school.acl.entity.pojo.EduCourse;
import com.school.acl.entity.vo.CourseInfoVo;
import com.school.acl.entity.vo.CourseQuery;
import com.school.acl.service.EduChapterService;
import com.school.acl.service.EduCourseService;
import com.school.common.exception.MyException;
import com.school.common.response.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
@Api("课程管理")
@RestController
@RequestMapping("/admin/acl/course")
public class CourseController {


    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    /**
     *  获取课程列表
     * @param current  当前页
     * @param limit   每页显示条数
     * @return
     */
    @PostMapping("pageCourse/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) CourseQuery query) {
        return eduCourseService.pageCourseCondition(current,limit,query);
    }

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    //课程大纲列表,根据课程id进行查询
    @GetMapping("getChapter/{courseId}")
    public R getChapter(@PathVariable String courseId) {
        List<EduChapter> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("list",list);
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    //删除的方法
    @DeleteMapping("/delete/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }
    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        EduCourse vo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",vo);
    }
    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        EduCourse courseInfoVo = eduCourseService.getById(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    //根据课程id查询课程基本信息
    @DeleteMapping("deleteCourseById/{courseId}")
    public R deleteCourseById(@PathVariable String courseId) {
        try {
            boolean b = eduCourseService.removeById(courseId);
            if(b){
              eduChapterService.remove(new QueryWrapper<EduChapter>().eq("course_id",courseId));
            }
            return R.ok();
        }catch (Exception e){
            throw new MyException(300002,"删除课程失败！");
        }
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody EduCourse vo) {
        eduCourseService.updateById(vo);
        return R.ok();
    }
}


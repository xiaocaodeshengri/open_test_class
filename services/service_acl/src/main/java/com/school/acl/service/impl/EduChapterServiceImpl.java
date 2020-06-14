package com.school.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.acl.entity.pojo.EduChapter;
import com.school.acl.mapper.EduChapterMapper;
import com.school.acl.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.common.exception.MyException;
import org.springframework.stereotype.Service;

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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Override
    public List<EduChapter> getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
        return eduChapterList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //删除章节
        int result = baseMapper.deleteById(chapterId);
        if (result>0){
            return  true;
        }else {
            throw new MyException(30000,"删除章节出问题");
        }
    }
}

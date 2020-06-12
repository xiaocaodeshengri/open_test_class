package com.school.acl.service;

import com.school.acl.entity.pojo.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     *依据课程ID获取章节
     * @param courseId
     * @return
     */
    List<EduChapter> getChapterVideoByCourseId(String courseId);

    /**
     *依据ID删除章节
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);
}

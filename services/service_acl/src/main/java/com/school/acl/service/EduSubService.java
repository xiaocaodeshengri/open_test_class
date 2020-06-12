package com.school.acl.service;

import com.school.acl.entity.pojo.EduSub;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.common.response.R;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
public interface EduSubService extends IService<EduSub> {
    //获取订阅
    R pageSubCondition(long current, long limit, EduSub query);
   //获取未订阅
    R pageNotSubCondition(EduSub query);
   //订阅课程
    R addSub(String id);
}

package com.school.acl.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.acl.entity.pojo.Permission;
import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
public interface PermissionService extends IService<Permission> {

    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

}

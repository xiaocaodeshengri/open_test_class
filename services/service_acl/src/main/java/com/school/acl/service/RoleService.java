package com.school.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.acl.entity.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
public interface RoleService extends IService<Role> {

    List<Role> selectRoleByUserId(String id);
}

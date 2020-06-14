package com.school.acl.mapper;

import com.school.acl.entity.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author cwh
 * @since 2020-06-10
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionByUserId(String userId);
}

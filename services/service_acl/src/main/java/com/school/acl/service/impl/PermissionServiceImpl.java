package com.school.acl.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.acl.entity.pojo.Permission;
import com.school.acl.entity.pojo.RolePermission;
import com.school.acl.entity.pojo.User;
import com.school.acl.helper.MemuHelper;
import com.school.acl.helper.PermissionHelper;
import com.school.acl.mapper.PermissionMapper;
import com.school.acl.service.PermissionService;
import com.school.acl.service.RolePermissionService;
import com.school.acl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author cwh
 * @since 2020-06-11
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {


    
    @Autowired
    private UserService userService;




    //根据用户id获取用户菜单
    @Override
    public List<String> selectPermissionValueByUserId(String id) {

        List<Permission> selectPermissionValueList = null;
        List<String> result =new ArrayList<>();
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectList(null);
            for (Permission per:selectPermissionValueList) {
                if(!StringUtils.isEmpty(per.getPermissionValue())) {
                    System.out.println(per.getPermissionValue());
                    result.add(per.getPermissionValue());
                }
            }
          //  selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
           // selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return result;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<Permission> selectPermissionList = null;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
        }

        List<Permission> permissionList = PermissionHelper.bulid(selectPermissionList);
        List<JSONObject> result = MemuHelper.bulid(permissionList);
        return result;
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);

        if(null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }








}

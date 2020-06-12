package com.school.acl.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQuery {

    @ApiModelProperty(value = "用户名称,模糊查询")
    private String username;

    @ApiModelProperty(value = "用户类型 1老师 2学生")
    private Integer type;


}

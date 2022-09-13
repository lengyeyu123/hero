package com.han.hero.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleUpdateReqVo {

    @NotNull
    private Integer roleId;

    private String roleName;

    private Integer orderNum;

    private Integer state;

    private String remark;

}

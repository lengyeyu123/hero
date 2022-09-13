package com.han.hero.project.vo.req;

import com.han.hero.common.enums.StateEnums;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleUpdateReqVo {

    @NotNull
    private Integer roleId;

    private String roleName;

    private Integer orderNum;

    private StateEnums state;

    private String remark;

}

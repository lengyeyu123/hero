package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleUpdateReqVo {

    @NotNull
    private Integer id;

    private String roleName;

    private Integer orderNum;

    private DelFlagEnums state;

    private String remark;

}

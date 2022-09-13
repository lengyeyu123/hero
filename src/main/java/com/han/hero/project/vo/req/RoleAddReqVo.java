package com.han.hero.project.vo.req;

import com.han.hero.common.enums.StateEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleAddReqVo {

    @NotBlank
    private String roleName;

    private Integer orderNum;

    private StateEnums state;

    private String remark;

}

package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleAddReqVo {

    @NotBlank
    private String name;

    private Integer orderNum;

    private DelFlagEnums delFlag;

    private String remark;

}

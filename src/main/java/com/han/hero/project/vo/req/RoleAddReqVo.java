package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleAddReqVo {

    @NotBlank
    private String name;

    private Integer orderNum;

    private DelFlagEnums delFlag;

    private String remark;

}

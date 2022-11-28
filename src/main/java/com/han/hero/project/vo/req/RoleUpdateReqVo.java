package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleUpdateReqVo {

    @NotNull
    private Integer id;

    private String name;

    private Integer orderNum;

    private DelFlagEnums delFlag;

    private String remark;

}

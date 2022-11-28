package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlag;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleUpdateReqVo {

    @NotNull
    private Integer id;

    private String name;

    private Integer orderNum;

    private DelFlag delFlag;

    private String remark;

}

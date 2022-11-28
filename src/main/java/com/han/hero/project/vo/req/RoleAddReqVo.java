package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleAddReqVo {

    @NotBlank
    private String name;

    private Integer orderNum;

    private DelFlag delFlag;

    private String remark;

}

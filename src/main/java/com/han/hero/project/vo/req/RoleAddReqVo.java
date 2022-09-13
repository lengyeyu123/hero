package com.han.hero.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleAddReqVo {

    @NotBlank
    private String roleName;

    private Integer orderNum;

    private Integer state;

    private String remark;

}

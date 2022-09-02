package com.han.hero.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Role {

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色排序序号
     */
    private Integer orderNum;

    /**
     * 角色状态 0禁用 1正常
     */
    private Integer state;


}

package com.han.hero.project.domain;

import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

@Data
public class Role extends BaseDomain {

    /**
     * 角色Id
     */
    private Integer id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色排序序号
     */
    private Integer orderNum;

}

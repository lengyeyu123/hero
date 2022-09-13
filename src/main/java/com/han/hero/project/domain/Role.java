package com.han.hero.project.domain;

import com.han.hero.common.enums.StateEnums;
import com.han.hero.framework.web.BaseDomain;

public class Role extends BaseDomain {

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
    private StateEnums state;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public StateEnums getState() {
        return state;
    }

    public void setState(StateEnums state) {
        this.state = state;
    }
}

package com.han.hero.project.domain;

import com.han.hero.common.enums.DelFlagEnums;
import com.han.hero.framework.web.BaseDomain;

public class User extends BaseDomain {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 删除状态 0已删除 1正常
     */
    private DelFlagEnums delFlag;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DelFlagEnums getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(DelFlagEnums delFlag) {
        this.delFlag = delFlag;
    }
}

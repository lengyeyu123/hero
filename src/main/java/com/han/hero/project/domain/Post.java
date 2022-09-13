package com.han.hero.project.domain;

import com.han.hero.common.enums.StateEnums;
import com.han.hero.framework.web.BaseDomain;

public class Post extends BaseDomain {

    /**
     * 岗位ID
     */
    private Integer postId;

    /**
     * 岗位编号
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 状态 0禁用 1正常
     */
    private StateEnums state;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getState() {
        return state.getCode();
    }

    public void setState(Integer stateCode) {
        this.state = StateEnums.get(stateCode);
    }
}

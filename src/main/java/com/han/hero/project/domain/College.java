package com.han.hero.project.domain;

import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

/**
 * 院系
 */
@Data
public class College extends BaseDomain {

    /**
     * 院系ID
     */
    private Integer id;

    /**
     * 院系编号
     */
    private String code;

    /**
     * 院系名称
     */
    private String name;

    /**
     * 院系管理员id
     * 不可重复 不能同时为多个院系的管理员
     */
    private Integer adminUserId;

}

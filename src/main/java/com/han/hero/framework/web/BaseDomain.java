package com.han.hero.framework.web;

import com.han.hero.common.enums.DelFlag;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDomain {

    /**
     * 创建用户ID
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户ID
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0禁用 1正常
     */
    private DelFlag delFlag;

}

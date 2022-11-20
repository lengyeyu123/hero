package com.han.hero.project.domain;

import com.han.hero.common.enums.OrganType;
import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

/**
 * 组织机构
 */
@Data
public class Organ extends BaseDomain {

    private Integer id;

    // 机构代码 系统生成
    private String code;

    // 机构名称
    private String name;

    // 机构类型
    private OrganType type;

    // 邮箱地址
    private String email;

    // 机构地址
    private String address;

    // 联系人
    private String linkman;

    // 联系人手机号
    private String linkmanPhone;

}

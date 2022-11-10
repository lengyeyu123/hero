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

    private String name;

    private OrganType organType;

    private String email;

    private String address;

    private String linkman;

    private String linkmanPhone;

}

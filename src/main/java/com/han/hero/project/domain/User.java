package com.han.hero.project.domain;

import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

@Data
public class User extends BaseDomain {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

}

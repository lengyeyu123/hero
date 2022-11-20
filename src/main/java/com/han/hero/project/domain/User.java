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
     * 用户名(教师工号)
     */
    private String userName;

    /**
     * 教师真实姓名
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

}

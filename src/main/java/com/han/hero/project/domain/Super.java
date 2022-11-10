package com.han.hero.project.domain;

import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

@Data
public class Super extends BaseDomain {

    private Integer id;

    private String userName;

    private String password;

}

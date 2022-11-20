package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import com.han.hero.common.enums.MenuType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuUpdateReqVo {

    @NotNull
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private MenuType menuType;

    private DelFlagEnums state;

    private String perms;

    private String icon;


}

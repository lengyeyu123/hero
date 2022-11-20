package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import com.han.hero.common.enums.MenuType;
import lombok.Data;

@Data
public class MenuListReqVo {

    private String name;

    private String parentName;

    private String path;

    private String component;

    private DelFlagEnums state;

    private MenuType type;

    private String perms;

    private Integer pageNum;

    private Integer pageSize;

}

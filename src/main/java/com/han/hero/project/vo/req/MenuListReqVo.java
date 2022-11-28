package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlag;
import com.han.hero.common.enums.MenuType;
import lombok.Data;

@Data
public class MenuListReqVo {

    private String name;

    private String parentName;

    private String path;

    private String component;

    private DelFlag state;

    private MenuType type;

    private String perms;

    private Integer pageNum;

    private Integer pageSize;

}

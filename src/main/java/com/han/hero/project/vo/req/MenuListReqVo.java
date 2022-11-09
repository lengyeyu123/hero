package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

@Data
public class MenuListReqVo {

    private String menuName;

    private String parentName;

    private String path;

    private String component;

    private DelFlagEnums state;

    private String menuType;

    private String perms;

    private Integer pageNum;

    private Integer pageSize;

}

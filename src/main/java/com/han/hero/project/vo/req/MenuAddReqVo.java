package com.han.hero.project.vo.req;

import lombok.Data;

@Data
public class MenuAddReqVo {

    private String menuName;

    private Integer parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private String menuType;

    private Integer state;

    private String perms;

    private String icon;

}

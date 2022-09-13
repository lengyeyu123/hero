package com.han.hero.project.vo.req;

import lombok.Data;

@Data
public class MenuListReqVo {

    private String menuName;

    private String parentName;

    private String path;

    private String component;

    private Integer state;

    private String menuType;

    private String perms;

    private Integer pageNum;

    private Integer pageSize;

}

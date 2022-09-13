package com.han.hero.project.vo.req;

import com.han.hero.common.enums.StateEnums;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuUpdateReqVo {

    @NotNull
    private Integer muenId;

    private String menuName;

    private Integer parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private String menuType;

    private StateEnums state;

    private String perms;

    private String icon;


}
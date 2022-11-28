package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlag;
import com.han.hero.common.enums.MenuType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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

    private DelFlag state;

    private String perms;

    private String icon;


}

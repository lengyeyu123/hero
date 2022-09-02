package com.han.hero.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleMenu {

    private Integer roleId;

    private Integer menuId;

}

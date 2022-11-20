package com.han.hero.project.domain;

import com.han.hero.common.enums.MenuType;
import com.han.hero.framework.web.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Menu extends BaseDomain {

    /**
     * 菜单Id
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 排序序号
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 菜单类型 (M目录 C菜单 F按钮)
     */
    private MenuType menuType;

    /**
     * 权限字符 *:*:*拥有所有权限 super
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<Menu> children = new ArrayList<>();

}

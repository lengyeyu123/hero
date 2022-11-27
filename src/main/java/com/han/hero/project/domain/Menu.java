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
     * 菜单编码:name
     */
    private String code;

    /**
     * 菜单名称:Meta.title
     */
    private String name;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 排序序号:Meta.order
     */
    private Integer orderNum;

    /**
     * 路由地址:path
     */
    private String path;

    /**
     * 路由的组件:component
     * - basic - 基础布局，具有公共部分的布局
     * - blank - 空白布局
     * - multi - 多级路由布局(三级路由或三级以上时，除第一级路由和最后一级路由，其余的采用该布局)
     * - self - 作为子路由，使用自身的布局(作为最后一级路由，没有子路由)
     */
    private String component;

    /**
     * 菜单类型 (0M目录 1C菜单 2F按钮)
     */
    private MenuType type;

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

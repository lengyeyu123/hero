package com.han.hero.project.vo.resp;

import lombok.Data;

@Data
public class RouteMeta {

    /**
     * 路由标题(可用来作document.title或者菜单的名称)
     */
    private String title;

    /**
     * 路由的动态路径
     */
    private String dynamicPath;

    /**
     * 作为单级路由的父级路由布局组件
     */
    private String singleLayout;

    /**
     * 需要登录权限
     */
    private Boolean requiresAuth;

    /**
     * 缓存页面(开启缓存只需要对最后一级的路由添加该属性)
     */
    private Boolean keepAlive;

    /**
     * 菜单和面包屑对应的图标
     */
    private String icon;

    /**
     * 使用本地svg作为的菜单和面包屑对应的图标(assets/svg-icon文件夹的的svg文件名)
     */
    private String localIcon;

    /**
     * 是否在菜单中隐藏
     */
    private Boolean hide;

    /**
     * 外链链接
     */
    private String href;

    /**
     * 是否支持多个tab页签(默认一个，即相同name的路由会被替换)
     */
    private Boolean multiTab;

    /**
     * 路由顺序，可用于菜单的排序
     */
    private Integer order;

    /**
     * 当前路由需要选中的菜单项(用于跳转至不在左侧菜单显示的路由且需要高亮某个菜单的情况)
     */
    private String activeMenu;

    /**
     * 表示是否是多级路由的中间级路由(用于转换路由数据时筛选多级路由的标识，定义路由时不用填写)
     */
    private Boolean multi;


}

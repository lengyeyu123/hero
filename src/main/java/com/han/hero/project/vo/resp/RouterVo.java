package com.han.hero.project.vo.resp;

import lombok.Data;

import java.util.List;

@Data
public class RouterVo {

    /**
     * 路由名称(路由唯一标识)
     */
    private String name;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 路由重定向
     */
    private String redirect;

    /**
     * 路由组件
     * - basic: 基础布局，具有公共部分的布局
     * - blank: 空白布局
     * - multi: 多级路由布局(三级路由或三级以上时，除第一级路由和最后一级路由，其余的采用该布局)
     * - self: 作为子路由，使用自身的布局(作为最后一级路由，没有子路由)
     */
    private String component;

    /**
     * 子路由
     */
    private RouteMeta meta;

    /**
     * 子路由
     */
    private List<RouterVo> children;

}

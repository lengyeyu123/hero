package com.han.hero.project.domain;

import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

@Data
public class Post extends BaseDomain {

    /**
     * 岗位ID
     */
    private Integer id;

    /**
     * 岗位编号
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 排序编号
     */
    private Integer orderNum;

}

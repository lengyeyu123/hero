package com.han.hero.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Post {

    /**
     * 岗位ID
     */
    private Integer postId;

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

    /**
     * 状态 0禁用 1正常
     */
    private Integer state;

}

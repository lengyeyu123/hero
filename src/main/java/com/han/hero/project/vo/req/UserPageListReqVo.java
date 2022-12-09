package com.han.hero.project.vo.req;

import lombok.Data;

@Data
public class UserPageListReqVo extends PageReqVo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 学院Id
     */
    private Integer collegeId;


}

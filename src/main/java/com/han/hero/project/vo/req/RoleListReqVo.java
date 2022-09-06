package com.han.hero.project.vo.req;

import lombok.Data;

@Data
public class RoleListReqVo {

    private Integer pageNum;

    private Integer pageSize;

    private String roleName;

}

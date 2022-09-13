package com.han.hero.project.vo.req;

import com.han.hero.common.enums.StateEnums;
import lombok.Data;

@Data
public class PostListReqVo {

    private String postCode;

    private String postName;

    private StateEnums state;

    private Integer pageNum;

    private Integer pageSize;

}

package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

@Data
public class PostListReqVo {

    private String postCode;

    private String postName;

    private DelFlagEnums state;

    private Integer pageNum;

    private Integer pageSize;

}

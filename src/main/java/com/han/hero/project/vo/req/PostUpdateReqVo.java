package com.han.hero.project.vo.req;

import com.han.hero.common.enums.StateEnums;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostUpdateReqVo {

    @NotNull
    private Integer postId;

    private String postName;

    private String postCode;

    private Integer orderNum;

    private StateEnums state;

}
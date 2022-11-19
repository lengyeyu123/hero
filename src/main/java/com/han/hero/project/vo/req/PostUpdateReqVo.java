package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostUpdateReqVo {

    @NotNull
    private Integer id;

    private String postName;

    private String postCode;

    private Integer orderNum;

    private DelFlagEnums state;

}

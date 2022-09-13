package com.han.hero.project.vo.req;

import com.han.hero.common.enums.StateEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostAddReqVo {

    @NotBlank
    private String postCode;

    @NotBlank
    private String postName;

    private Integer orderNum;

    private StateEnums state;

}

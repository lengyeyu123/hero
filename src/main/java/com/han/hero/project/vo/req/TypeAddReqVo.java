package com.han.hero.project.vo.req;

import com.han.hero.common.enums.StateEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TypeAddReqVo {

    @NotBlank
    private String dictName;

    @NotBlank
    private String dictType;

    private String remark;

    private StateEnums state;

}

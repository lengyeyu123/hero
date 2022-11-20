package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DictTypeAddReqVo {

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    private String remark;

    private DelFlagEnums delFlag;

}

package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DataUpdateReqVo {

    @NotNull
    private Integer dictCode;

    private Integer orderNum;

    private String dictLabel;

    private String dictValue;

    private String dictType;

    private Integer defaultState;

    private DelFlagEnums state;

    private Integer updateBy;

    private String remark;

}

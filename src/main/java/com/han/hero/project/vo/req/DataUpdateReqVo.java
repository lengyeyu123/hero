package com.han.hero.project.vo.req;

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

    private Integer state;

    private Integer updateBy;

    private String remark;

}

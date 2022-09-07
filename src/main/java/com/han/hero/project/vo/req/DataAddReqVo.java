package com.han.hero.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DataAddReqVo {

    private Integer orderNum;

    @NotBlank
    private String dictLabel;

    @NotBlank
    private String dictValue;

    @NotBlank
    private String dictType;

    private Integer defaultState;

    private String remark;

    private Integer createBy;

}

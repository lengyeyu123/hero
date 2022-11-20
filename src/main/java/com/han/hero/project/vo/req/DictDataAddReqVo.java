package com.han.hero.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DictDataAddReqVo {

    private Integer orderNum;

    @NotBlank
    private String label;

    @NotBlank
    private String val;

    @NotBlank
    private String type;

    private Integer defaultState;

    private String remark;

    private Integer createBy;

}

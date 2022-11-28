package com.han.hero.project.vo.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


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

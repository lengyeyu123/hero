package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DictDataUpdateReqVo {

    @NotNull
    private Integer id;

    private Integer orderNum;

    private String label;

    private String val;

    private String type;

    private Integer defaultState;

    private DelFlagEnums state;

    private Integer updateBy;

    private String remark;

}

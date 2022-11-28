package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlag;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DictDataUpdateReqVo {

    @NotNull
    private Integer id;

    private Integer orderNum;

    private String label;

    private String val;

    private String type;

    private Integer defaultState;

    private DelFlag state;

    private Integer updateBy;

    private String remark;

}

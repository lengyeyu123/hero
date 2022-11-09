package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

@Data
public class TypeUpdateReqVo {

    private Integer dictId;

    private String dictName;

    private String dictType;

    private String remark;

    private DelFlagEnums state;

}

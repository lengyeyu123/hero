package com.han.hero.project.domain;

import com.han.hero.common.enums.DelFlagEnums;
import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

@Data
public class DictTypeData extends BaseDomain {

    private Integer id;

    private Integer orderNum;

    private String dictLabel;

    private String dictValue;

    private String dictType;

    private Integer defaultState;

    private DelFlagEnums delFlag;
}

package com.han.hero.project.domain;

import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

@Data
public class DictData extends BaseDomain {

    private Integer id;

    private Integer orderNum;

    private String label;

    private String val;

    private String type;

    private Integer defaultState;
}

package com.han.hero.project.domain;

import com.han.hero.common.enums.StateEnums;
import com.han.hero.framework.web.BaseDomain;

public class DictType extends BaseDomain {

    private Integer dictId;

    private String dictName;

    private String dictType;

    private StateEnums state;

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public StateEnums getState() {
        return state;
    }

    public void setState(StateEnums state) {
        this.state = state;
    }
}

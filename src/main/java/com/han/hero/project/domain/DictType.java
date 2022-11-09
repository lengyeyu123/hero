package com.han.hero.project.domain;

import com.han.hero.common.enums.DelFlagEnums;
import com.han.hero.framework.web.BaseDomain;

public class DictType extends BaseDomain {

    /**
     * 字典ID
     */
    private Integer dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 删除标记 0被删除 1正常
     */
    private DelFlagEnums delFlag;

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

    public DelFlagEnums getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(DelFlagEnums delFlag) {
        this.delFlag = delFlag;
    }
}

package com.han.hero.project.vo.req;

import lombok.Data;

@Data
public class TypeUpdateReqVo {

    private Integer dictId;

    private String dictName;

    private String dictType;

    private String remark;

    private Integer state;

}

package com.han.hero.project.vo.req;

import lombok.Data;

@Data
public class TypeListReqVo {

    private Integer pageNum;

    private Integer pageSize;

    private String dictName;

    private String dictType;


}

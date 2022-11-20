package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlagEnums;
import lombok.Data;

@Data
public class DictTypeUpdateReqVo {

    private Integer id;

    private String name;

    private String type;

    private String remark;

    private DelFlagEnums delFlag;

}

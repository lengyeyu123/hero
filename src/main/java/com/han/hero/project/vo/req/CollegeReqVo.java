package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlag;
import lombok.Data;

@Data
public class CollegeReqVo extends PageReqVo {

    private Integer id;

    private String code;

    private String name;

    private Integer adminUserId;

    private String remark = "";

    private Integer createBy;

    private Integer updateBy;

    private DelFlag delFLag;

}

package com.han.hero.project.vo.req;

import com.han.hero.common.enums.DelFlag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DictTypeAddReqVo {

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    private String remark;

    private DelFlag delFlag;

}

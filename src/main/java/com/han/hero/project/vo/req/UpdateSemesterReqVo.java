package com.han.hero.project.vo.req;

import com.han.hero.common.enums.OpenStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSemesterReqVo {

    @NotNull
    private Integer id;

    @NotNull
    private OpenStatus openStatus;

}

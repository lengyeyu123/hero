package com.han.hero.project.vo.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginReqVo {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}

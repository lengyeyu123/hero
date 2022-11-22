package com.han.hero.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReqVo {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}

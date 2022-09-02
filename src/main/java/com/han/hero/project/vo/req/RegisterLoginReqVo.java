package com.han.hero.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterLoginReqVo {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}

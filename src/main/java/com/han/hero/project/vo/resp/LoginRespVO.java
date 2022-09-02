package com.han.hero.project.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRespVO {

    private String accessToken;

    private String refreshToken;

}

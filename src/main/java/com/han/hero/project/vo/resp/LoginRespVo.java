package com.han.hero.project.vo.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRespVo {

    private String accessToken;

    private String refreshToken;

}

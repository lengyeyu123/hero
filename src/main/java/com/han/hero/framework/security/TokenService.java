package com.han.hero.framework.security;

import com.han.hero.framework.config.properties.TokenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenService {

    @Autowired
    private TokenProperties tokenProperties;


}

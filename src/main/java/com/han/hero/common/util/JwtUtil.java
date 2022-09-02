package com.han.hero.common.util;

import com.han.hero.framework.config.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * jwt工具类
 */
public class JwtUtil {

    /**
     * 生成JWT
     */
    public static String generateJwt(TokenProperties.TokenConfig tokenConfig, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + tokenConfig.getExpireTime() * 60 * 1000L))
                .signWith(SignatureAlgorithm.HS512, tokenConfig.getSecret().getBytes())
                .compact();
    }

    public static Claims parseJwt(String jwt, String secret) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
    }

}

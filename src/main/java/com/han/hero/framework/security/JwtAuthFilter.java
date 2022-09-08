package com.han.hero.framework.security;

import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.util.JsonUtil;
import com.han.hero.common.util.JwtUtil;
import com.han.hero.common.util.ResponseWriterUtil;
import com.han.hero.common.web.domain.R;
import com.han.hero.framework.config.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解析请求头中的token 放入到 spring security中 若没有则放行
 */
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private TokenProperties tokenProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        TokenProperties.TokenConfig atConfig = tokenProperties.getAtConfig();
        // TODO header 字段问题 Authorization
        // String header = request.getHeader(atConfig.getHeader());
        String header = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(header)) {
            Claims claims;
            R<?> r = new R<>();
            try {
                claims = JwtUtil.parseJwt(header, atConfig.getSecret());
            } catch (ExpiredJwtException expiredJwtException) {
                log.error("token过期", expiredJwtException);
                r.setCode(ResultStatus.TOKEN_EXPIRED.getCode());
                r.setMessage(ResultStatus.TOKEN_EXPIRED.getMessage());
                ResponseWriterUtil.write(response, JsonUtil.toJson(r));
                // TODO 这个return的含义？？ 没搞懂
                return;
            } catch (Exception exception) {
                log.error("token解析发生异常", exception);
                r.setCode(ResultStatus.SERVER_ERROR.getCode());
                r.setMessage(ResultStatus.SERVER_ERROR.getMessage());
                r.setDefaultMsg(exception.getMessage());
                ResponseWriterUtil.write(response, JsonUtil.toJson(r));
                return;
            }
            if (claims != null) {
                String userName = (String) claims.get("userName");
                if (StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("Authenticated user " + userName + ", setting security context!");
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

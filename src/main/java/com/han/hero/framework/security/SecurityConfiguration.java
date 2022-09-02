package com.han.hero.framework.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
// 添加security过滤器
@EnableWebSecurity
// 启用方法级别的权限认证
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    // 权限不足错误信息处理，包含认证错误与鉴权错误处理
    @Autowired
    private JwtAuthError jwtAuthError;

    /**
     * jwt 校验过滤器，从 http 头部 Authorization 字段读取 token 并校验
     */
    @Bean
    public JwtAuthFilter authFilter() throws Exception {
        return new JwtAuthFilter();
    }

    /**
     * 密码明文加密方式配置
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取 AuthenticationManager (认证管理器) , 登录认证使用
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .cors().and()
                // 基于 token, 不需要csrf
                .csrf().disable()
                // 基于 token 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 设置 jwtAuthError 处理认证失败 鉴权失败
                .exceptionHandling().authenticationEntryPoint(jwtAuthError).accessDeniedHandler(jwtAuthError).and()
                // 开始设置权限
                .authorizeRequests(authorize -> {
                            // 请求放开
                            try {
                                authorize.antMatchers("/auth/login").permitAll()
                                        .antMatchers("/auth/register").permitAll()
                                        // 其他地址的访问均需要验证权限
                                        .anyRequest().authenticated()
                                        // 禁用formLogin
                                        .and().formLogin().disable();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                // 添加 JWT 过滤器，JWT过滤器在用户名密码认证过滤器之前
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                // 认证用户时用户信息加载配置， 注入springAuthUserService
                .userDetailsService(userDetailService)
                .build();
    }


}

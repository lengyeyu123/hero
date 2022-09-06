package com.han.hero.project.controller;

import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.common.util.JwtUtil;
import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.framework.config.properties.TokenProperties;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.User;
import com.han.hero.project.service.UserService;
import com.han.hero.project.vo.req.RegisterLoginReqVo;
import com.han.hero.project.vo.resp.LoginRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/register")
    public R<?> register(@RequestBody @Validated RegisterLoginReqVo vo) {
        User user = new User();
        user.setUserName(vo.getUserName());
        user.setPassword(passwordEncoder.encode(vo.getPassword()));
        if (userService.validateUserName(vo.getUserName())) {
            userService.insertUser(user);
        } else {
            throw new ServiceException(ResultStatus.ACCOUNT_USER_NAME_REPEAT);
        }
        return R.ok();
    }

    @PostMapping("/login")
    public R<LoginRespVO> login(@RequestBody @Validated RegisterLoginReqVo vo) {
        User user = userService.selectByUserName(vo.getUserName());
        if (user == null) {
            throw new ServiceException(ResultStatus.ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        } else {
            if (passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
                // 登录成功
                Map<String, Object> claims = new HashMap<>();
                claims.put("userName", user.getUserName());

                String accessToken = JwtUtil.generateJwt(tokenProperties.getAtConfig(), claims);
                String refreshToken = JwtUtil.generateJwt(tokenProperties.getRtConfig(), claims);
                return R.ok(new LoginRespVO(accessToken, refreshToken));
            } else {
                throw new ServiceException(ResultStatus.ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
            }
        }
    }

    // 三种注释都可以
//    @PreAuthorize("hasAuthority('sys:role:list')")
//    @PreAuthorize("hasRole('super')")
//    @PreAuthorize("hasRole('ROLE_super')")
    @Log(title = "认证", businessType = BusinessType.OTHER)
    @PreAuthorize("hasRole('super')")
    @GetMapping("/getUserInfo")
    public R<User> getUserInfo() {
        String userName = SecurityUtil.getUserName();
        User user = userService.selectByUserName(userName);
        redisTemplate.opsForValue().set(userName, user);
        return R.ok(user);
    }


}

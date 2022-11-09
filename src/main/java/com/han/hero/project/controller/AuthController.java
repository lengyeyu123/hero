package com.han.hero.project.controller;

import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.enums.StateEnums;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.common.util.JwtUtil;
import com.han.hero.common.util.RedisUtil;
import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.framework.config.properties.TokenProperties;
import com.han.hero.framework.security.LoginUser;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.User;
import com.han.hero.project.service.UserService;
import com.han.hero.project.vo.req.RegisterLoginReqVo;
import com.han.hero.project.vo.resp.LoginRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/register")
    public R<?> register(@RequestBody @Validated RegisterLoginReqVo vo) {
        User user = new User();
        user.setUserName(vo.getUsername());
        user.setPassword(passwordEncoder.encode(vo.getPassword()));
        if (userService.validateUserName(vo.getUsername())) {
            userService.insertUser(user);
        } else {
            throw new ServiceException(ResultStatus.ACCOUNT_USER_NAME_REPEAT);
        }
        return R.ok();
    }

    @PostMapping("/login")
    public R<LoginRespVO> login(@RequestBody @Validated RegisterLoginReqVo vo) {
        User user = userService.selectByUserName(vo.getUsername());
        if (user == null) {
            throw new ServiceException(ResultStatus.ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        } else {
            if (passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
                if (user.getState() == StateEnums.DISABLED) {
                    return R.fail(ResultStatus.ACCOUNT_DISABLED);
                }
                // 登录成功
                Map<String, Object> claims = new HashMap<>();
                claims.put("userName", user.getUserName());

                String accessToken = JwtUtil.generateJwt(tokenProperties.getAtConfig(), claims);
                String refreshToken = JwtUtil.generateJwt(tokenProperties.getRtConfig(), claims);
                LoginRespVO respVO = new LoginRespVO();
                respVO.setToken(accessToken);
                respVO.setUserId(user.getUserId());
                respVO.setUsername(user.getUserName());
                respVO.setRealName(user.getUserName());
                respVO.setDesc(user.getRemark());
                respVO.setHomePath("/dashboard/analysis");
                Map<String, String> roleMap = new HashMap<>();
                roleMap.put("roleName", "Super Admin");
                roleMap.put("value", "super");
                respVO.getRoles().add(roleMap);
                return R.ok(respVO);
            } else {
                throw new ServiceException(ResultStatus.ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
            }
        }
    }

    // 三种注释都可以
    // @PreAuthorize("hasAuthority('sys:role:list')")
    // @PreAuthorize("hasRole('super')")
    // @PreAuthorize("hasRole('super')")
    @Log(title = "认证", businessType = BusinessType.OTHER)
    @PreAuthorize("hasRole('super')")
    @GetMapping("/getUserInfo")
    public R<?> getUserInfo() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        redisUtil.set(loginUser.getUsername(), loginUser);
        LoginRespVO vo = new LoginRespVO();
        vo.setUserId(loginUser.getUser().getUserId());
        vo.setUsername(loginUser.getUsername());
        vo.setRealName(loginUser.getUsername());
        vo.setAvatar("https://picsum.photos/id/1005/100/100.webp");
        vo.setDesc(loginUser.getUser().getRemark());
        vo.setHomePath("/dashboard/analysis");
        Map<String, String> roleMap = new HashMap<>();
        roleMap.put("roleName", "Super Admin");
        roleMap.put("value", "super");
        vo.getRoles().add(roleMap);
        return R.ok(vo);
    }


}

package com.han.hero.project.controller;

import com.han.hero.common.constants.ClaimsConstants;
import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.common.util.JwtUtil;
import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.framework.config.properties.TokenProperties;
import com.han.hero.framework.security.LoginUser;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.User;
import com.han.hero.project.service.RoleService;
import com.han.hero.project.service.UserService;
import com.han.hero.project.vo.req.LoginReqVo;
import com.han.hero.project.vo.resp.LoginRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RoleService roleService;

    @Log(title = "登录成功，获取用户信息", businessType = BusinessType.GRANT)
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody @Validated LoginReqVo vo) {
        User user = userService.selectByUserName(vo.getUserName());
        if (user == null) {
            throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        }
        if (!passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
            throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put(ClaimsConstants.USER_NAME_KEY, user.getUserName());
        String accessToken = JwtUtil.generateJwt(tokenProperties.getAtConfig(), claims);
        String refreshToken = JwtUtil.generateJwt(tokenProperties.getRtConfig(), claims);
        return R.ok(
                new LoginRespVo()
                        .setToken(accessToken)
                        .setRefreshToken(refreshToken)
        );
    }

    /**
     * 登录成功获取用户信息
     */
    @Log(title = "登录成功，获取用户信息", businessType = BusinessType.GRANT)
    @PostMapping("/getUserInfo")
    public R<?> getUserInfo() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        User user = loginUser.getUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", user.getUserName());
        map.put("realName", user.getRealName());

        if (user.getId() == 1) {
            // 超级管理员
            List<Role> list = roleService.all();
            map.put("roles", list.stream().map(Role::getCode).collect(Collectors.toSet()));
            HashSet<String> perms = new HashSet<>();
            perms.add("*:*:*");
            map.put("perms", perms);
        } else {
            map.put("roles", loginUser.getRoles().stream().map(Role::getCode).collect(Collectors.toSet()));
            map.put("perms", loginUser.getMenus().stream().map(Menu::getPerms).collect(Collectors.toSet()));
        }
        return R.ok(map);
    }


    // 三种注释都可以
    // @PreAuthorize("hasAuthority('sys:role:list')")
    // @PreAuthorize("hasRole('ROLE_super')")
    // @PreAuthorize("hasRole('super')")

}

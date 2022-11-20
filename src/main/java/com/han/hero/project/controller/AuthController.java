package com.han.hero.project.controller;

import com.han.hero.common.constants.ClaimsConstants;
import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.common.util.JwtUtil;
import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.framework.config.properties.HeroProperties;
import com.han.hero.framework.config.properties.TokenProperties;
import com.han.hero.framework.datasource.DynamicDataSourceConfig;
import com.han.hero.framework.db.InitDB;
import com.han.hero.framework.security.LoginUser;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.User;
import com.han.hero.project.service.UserService;
import com.han.hero.project.vo.req.LoginReqVo;
import com.han.hero.project.vo.resp.LoginRespVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    private DynamicDataSourceConfig dynamicDataSourceConfig;

    @Autowired
    private HeroProperties heroProperties;

    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody @Validated LoginReqVo vo) {
        if (StringUtils.isNotBlank(vo.getOrganCode())) {
            // 普通机构用户
            // 根据机构代码添加数据源
            String dbName = InitDB.organDbName(vo.getOrganCode());
            dynamicDataSourceConfig.addDataSource(dbName, false);
            User user = userService.selectByUserName(dbName, vo.getUserName());
            if (user == null) {
                throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
            } else {
                if (!passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
                    throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
                } else {
                    // 登录成功
                    Map<String, Object> claims = new HashMap<>();
                    claims.put(ClaimsConstants.ORGAN_CODE_KEY, vo.getOrganCode());
                    claims.put(ClaimsConstants.USER_NAME_KEY, user.getUserName());
                    String accessToken = JwtUtil.generateJwt(tokenProperties.getAtConfig(), claims);
                    String refreshToken = JwtUtil.generateJwt(tokenProperties.getRtConfig(), claims);
                    return R.ok(
                            new LoginRespVo()
                                    .setToken(accessToken)
                                    .setRefreshToken(refreshToken)
                    );
                }
            }
        } else {
            // 系统开发人员super2022
            if (vo.getUserName().equals("super2022")) {
                User user = userService.selectByUserName(heroProperties.getDbName(), vo.getUserName());
                if (user == null) {
                    throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
                } else {
                    if (passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
                        Map<String, Object> claims = new HashMap<>();
                        claims.put(ClaimsConstants.USER_NAME_KEY, user.getUserName());
                        String accessToken = JwtUtil.generateJwt(tokenProperties.getAtConfig(), claims);
                        String refreshToken = JwtUtil.generateJwt(tokenProperties.getRtConfig(), claims);
                        return R.ok(
                                new LoginRespVo()
                                        .setToken(accessToken)
                                        .setRefreshToken(refreshToken)
                        );
                    } else {
                        throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
                    }
                }
            } else {
                throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
            }
        }

    }

    /**
     * 登录成功获取用户信息
     */
    @Log(title = "登录成功，获取用户信息", businessType = BusinessType.OTHER)
    @PostMapping("/getUserInfo")
    public R<?> getUserInfo() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        User user = loginUser.getUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", user.getUserName());
        map.put("realName", user.getRealName());
        map.put("roles", loginUser.getRoles().stream().map(Role::getCode).collect(Collectors.toSet()));
        map.put("perms", loginUser.getMenus().stream().map(Menu::getPerms).collect(Collectors.toSet()));
        return R.ok(map);
    }


    // 三种注释都可以
    // @PreAuthorize("hasAuthority('sys:role:list')")
    // @PreAuthorize("hasRole('ROLE_super')")
    // @PreAuthorize("hasRole('super')")

}

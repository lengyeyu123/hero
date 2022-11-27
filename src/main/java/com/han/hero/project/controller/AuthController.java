package com.han.hero.project.controller;

import com.han.hero.common.constants.ClaimsConstants;
import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.enums.MenuType;
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
import com.han.hero.project.service.MenuService;
import com.han.hero.project.service.RoleService;
import com.han.hero.project.service.UserService;
import com.han.hero.project.vo.req.LoginReqVo;
import com.han.hero.project.vo.resp.LoginRespVo;
import com.han.hero.project.vo.resp.RouteMeta;
import com.han.hero.project.vo.resp.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

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
        map.put("userId", user.getId());
        map.put("userName", user.getUserName());
        map.put("realName", user.getRealName());

        if (user.getId() == 1) {
            // 超级管理员
            HashSet<String> perms = new HashSet<>();
            perms.add("super");
            perms.add("*:*:*");
            map.put("perms", perms);
        } else {
            Set<String> roleCodeSet = loginUser.getRoles().stream().map(Role::getCode).collect(Collectors.toSet());
            roleCodeSet.addAll(loginUser.getPerms());
            map.put("perms", roleCodeSet);
        }
        return R.ok(map);
    }


    // 三种注释都可以
    // @PreAuthorize("hasAuthority('sys:role:list')")
    // @PreAuthorize("hasRole('ROLE_super')")
    // @PreAuthorize("hasRole('super')")

    @PostMapping("/getUserRoutes")
    public R<?> getUserRoutes() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        List<Menu> menus = menuService.selectMenuTreeByUserId(loginUser.getUser().getId());
        menuService.treeData(menus, -1);
        ArrayList<RouterVo> routerVoList = new ArrayList<>();
        convertToRouter(routerVoList, menus);
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("home", "dashboard_analysis");
        reMap.put("routes", routerVoList);

        return R.ok(reMap);
    }

    private void convertToRouter(ArrayList<RouterVo> routerVoList, List<Menu> menus) {
        for (Menu menu : menus) {
            RouterVo routerVo = createRouterVo(menu);

            List<Menu> children = menu.getChildren();
            if (children != null && !children.isEmpty()) {
                List<RouterVo> childRouterList = new ArrayList<>();
                for (Menu child : children) {
                    childRouterList.add(createRouterVo(child));
                }
                routerVo.setChildren(childRouterList);
                routerVoList.add(routerVo);
                convertToRouter(routerVoList, children);
            } else if (menu.getParentId() == -1) {
                routerVoList.add(routerVo);
            }

        }
    }

    private RouterVo createRouterVo(Menu menu) {
        RouterVo routerVo = new RouterVo();
        routerVo.setName(menu.getCode());
        routerVo.setPath(menu.getPath());
        routerVo.setComponent(menu.getComponent());
        RouteMeta meta = new RouteMeta();
        meta.setKeepAlive(menu.getType() == MenuType.C);
        meta.setTitle(menu.getName());
        meta.setIcon(menu.getIcon());
        meta.setRequiresAuth(true);
        routerVo.setMeta(meta);
        return routerVo;
    }

}

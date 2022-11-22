package com.han.hero.framework.security;

import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.User;
import com.han.hero.project.mapper.MenuMapper;
import com.han.hero.project.mapper.RoleMapper;
import com.han.hero.project.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            log.error("loadUserByUsername 参数 username 为空");
            throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        }
        User user = userMapper.selectByUserName(username);
        if (Objects.isNull(user)) {
            log.info("用户名错误未查询到用户");
            throw new ServiceException(ResultStatus.AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        }

        List<Role> roles = null;
        List<Menu> menus = null;

        if (user.getId() == 1) {
            // 超级管理员
            roles = roleMapper.all();
            menus = menuMapper.all();
        } else {
            roles = roleMapper.selectByUserId(user.getId());
            menus = menuMapper.getRoleMenuByRoles(roles);
        }
        return new LoginUser(user, roles, menus);
    }


}

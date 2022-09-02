package com.han.hero.framework.security;

import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.User;
import com.han.hero.project.mapper.UserMapper;
import com.han.hero.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            log.error("loadUserByUsername 参数 username 为空");
            throw new ServiceException(ResultStatus.ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        }
        User user = userMapper.selectByUserName(username);
        if (Objects.isNull(user)) {
            log.info("用户名错误未查询到用户");
            throw new ServiceException(ResultStatus.ACCOUNT_ERROR_USER_NAME_OR_PASSWORD);
        }
        Set<Menu> permissions = userService.getUserPermissions(user.getUserId());
        return new LoginUser(user, permissions.stream().map(Menu::getPerms).collect(Collectors.toSet()));
    }


}

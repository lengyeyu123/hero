package com.han.hero.project.mapper;


import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.User;
import com.han.hero.project.domain.UserRole;

import java.util.List;
import java.util.Set;

public interface UserMapper {

    User selectById(Integer userId);

    User selectByUserName(String username);

    Set<Menu> getUserPermissions(Integer userId);

    void insertUser(User user);

    Integer countByUserName(String userName);

    List<UserRole> selectUserRoleByRoleId(Integer roleId);
}

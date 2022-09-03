package com.han.hero.project.mapper;

import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.Role;

import java.util.List;

public interface MenuMapper {

    List<Menu> getRoleMenuByRoles(List<Role> roles);

}

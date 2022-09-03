package com.han.hero.project.mapper;

import com.han.hero.project.domain.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> selectByUserId(Integer userId);

}

package com.han.hero.project.mapper;

import com.han.hero.project.domain.Role;
import com.han.hero.project.vo.req.RoleListReqVo;

import java.util.List;

public interface RoleMapper {

    List<Role> selectByUserId(Integer userId);

    List<Role> list(RoleListReqVo vo);

    void add(Role role);

    void update(Role role);

    void del(Integer roleId);
}

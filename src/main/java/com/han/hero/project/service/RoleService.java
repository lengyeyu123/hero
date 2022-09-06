package com.han.hero.project.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.han.hero.project.domain.Role;
import com.han.hero.project.mapper.RoleMapper;
import com.han.hero.project.vo.req.RoleListReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;


    public List<Role> list(RoleListReqVo vo) {
        Page<Object> page = PageHelper.startPage(vo);
        page.setOrderBy("orderNum");
        return roleMapper.list(vo);
    }

}

package com.han.hero.project.controller;

import com.han.hero.common.web.domain.R;
import com.han.hero.project.service.RoleService;
import com.han.hero.project.vo.req.RoleListReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色Controller
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表
     *
     * @param vo 角色名称
     */
    @PreAuthorize("hasAnyRole('ROLE_super', 'ROLE_admin')")
    @PostMapping("/list")
    public R<?> list(@RequestBody RoleListReqVo vo) {
        return R.ok(roleService.pageList(vo));
    }


}

package com.han.hero.project.controller;

import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.project.service.RoleService;
import com.han.hero.project.vo.req.RoleAddReqVo;
import com.han.hero.project.vo.req.RoleListReqVo;
import com.han.hero.project.vo.req.RoleUpdateReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     */
    @PreAuthorize("hasAnyRole('super', 'admin')")
    @PostMapping("/list")
    public R<?> list(@RequestBody RoleListReqVo vo) {
        return R.ok(roleService.pageList(vo));
    }

    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "新增角色")
    @PostMapping("/add")
    public R<?> add(@RequestBody @Validated RoleAddReqVo vo) {
        roleService.add(vo);
        return R.ok();
    }

    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "修改角色")
    @PostMapping("/update")
    public R<?> update(@RequestBody @Validated RoleUpdateReqVo vo) {
        roleService.update(vo);
        return R.ok();
    }

    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "删除角色")
    @GetMapping("/{roleId}/del")
    public R<?> del(@PathVariable("roleId") Integer roleId) {
        roleService.del(roleId);
        return R.ok();
    }


}

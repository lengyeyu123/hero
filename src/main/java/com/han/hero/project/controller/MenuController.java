package com.han.hero.project.controller;

import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.project.service.MenuService;
import com.han.hero.project.vo.req.MenuAddReqVo;
import com.han.hero.project.vo.req.MenuListReqVo;
import com.han.hero.project.vo.req.MenuUpdateReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 新增菜单
     */
    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "新增菜单")
    @PostMapping("/add")
    public R<?> add(@RequestBody @Validated MenuAddReqVo vo) {
        menuService.add(vo);
        return R.ok();
    }

    /**
     * 修改菜单信息
     */
    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "修改菜单")
    @PostMapping("/update")
    public R<?> update(@RequestBody @Validated MenuUpdateReqVo vo) {
        menuService.update(vo);
        return R.ok();
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "删除菜单")
    @GetMapping("/{menuId}/del")
    public R<?> del(@PathVariable("menuId") Integer menuId) {
        menuService.del(menuId);
        return R.ok();
    }

    @PostMapping("/list")
    public R<?> list(@RequestBody MenuListReqVo vo) {
        return R.ok(menuService.list(vo));
    }


}

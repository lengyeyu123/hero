package com.han.hero.project.controller;

import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.project.service.DictService;
import com.han.hero.project.vo.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 字典Controller
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 字典类型列表
     */
    @PostMapping("/type/list")
    public R<?> typeList(@RequestBody TypeListReqVo vo) {
        return R.ok(dictService.typePageList(vo));
    }

    @PreAuthorize("hasRole('super')")
    @Log(title = "新增字典类型", businessType = BusinessType.INSERT)
    @PostMapping("/type/add")
    public R<?> typeAdd(@RequestBody @Validated TypeAddReqVo vo) {
        dictService.typeAdd(vo);
        return R.ok();
    }

    @PreAuthorize("hasRole('super')")
    @Log(title = "修改字典类型", businessType = BusinessType.UPDATE)
    @PostMapping("/type/update")
    public R<?> typeUpdate(@RequestBody TypeUpdateReqVo vo) {
        dictService.typeUpdate(vo);
        return R.ok();
    }

    @PreAuthorize("hasRole('super')")
    @Log(title = "删除字典类型", businessType = BusinessType.DELETE)
    @GetMapping("/type/del")
    public R<?> typeDel(@RequestParam Integer dictId) {
        dictService.typeDel(dictId);
        return R.ok();
    }

    @PostMapping("/data/list")
    public R<?> dataList(@RequestBody DataListReqVo vo) {
        return R.ok(dictService.dataPageList(vo));
    }

    @PreAuthorize("hasRole('super')")
    @Log(title = "新增字典数据", businessType = BusinessType.INSERT)
    @PostMapping("/data/add")
    public R<?> dataAdd(@RequestBody @Validated DataAddReqVo vo) {
        dictService.dataAdd(vo);
        return R.ok();
    }

    @PreAuthorize("hasRole('super')")
    @Log(title = "删除字典数据", businessType = BusinessType.DELETE)
    @GetMapping("/data/del")
    public R<?> dataDel(@RequestParam Integer dictCode) {
        dictService.dataDel(dictCode);
        return R.ok();
    }

    @PreAuthorize("hasRole('super')")
    @Log(title = "修改字典数据", businessType = BusinessType.UPDATE)
    @PostMapping("/data/update")
    public R<?> dataUpdate(@RequestBody @Validated DataUpdateReqVo vo) {
        dictService.dataUpdate(vo);
        return R.ok();
    }

}

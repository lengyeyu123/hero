package com.han.hero.project.controller;

import com.han.hero.common.web.domain.R;
import com.han.hero.project.service.CollegeService;
import com.han.hero.project.vo.req.CollegeReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 院系管理
 */
@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @PostMapping("/pageList")
    public R<?> pageList(@RequestBody CollegeReqVo vo) {
        return R.ok(collegeService.pageList(vo));
    }

    @PostMapping("/add")
    public R<?> add(@RequestBody CollegeReqVo vo) {
        collegeService.add(vo);
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@RequestBody CollegeReqVo vo) {
        collegeService.update(vo);
        return R.ok();
    }

    @PostMapping("/del")
    public R<?> del(@RequestBody CollegeReqVo vo) {
        collegeService.del(vo);
        return R.ok();
    }

}

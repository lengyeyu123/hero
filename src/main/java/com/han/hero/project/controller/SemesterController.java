package com.han.hero.project.controller;

import com.han.hero.common.web.domain.R;
import com.han.hero.framework.security.LoginUser;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.service.SemesterService;
import com.han.hero.project.vo.req.UpdateSemesterReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学年学期自动创建 用户只能开启学期 关闭学期
 */
@RestController
@RequestMapping("/semester")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    /**
     * 开启/关闭学期
     */
    @PreAuthorize("hasAnyRole('super', 'admin')")
    @PostMapping("/updateStatus")
    public R<?> updateStatus(@RequestBody @Validated UpdateSemesterReqVo vo) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        List<String> perms = loginUser.getPerms();
        System.out.println(perms);
        semesterService.updateStatus(vo);
        return R.ok();
    }

}

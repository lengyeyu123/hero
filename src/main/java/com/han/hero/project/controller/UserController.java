package com.han.hero.project.controller;

import com.han.hero.common.web.domain.R;
import com.han.hero.project.service.UserService;
import com.han.hero.project.vo.req.UserPageListReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/pageList")
    public R<?> pageList(@RequestBody UserPageListReqVo vo) {
        return R.ok(userService.pageList(vo));
    }


}

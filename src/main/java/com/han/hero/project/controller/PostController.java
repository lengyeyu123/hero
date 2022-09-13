package com.han.hero.project.controller;

import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.web.domain.R;
import com.han.hero.framework.annotation.Log;
import com.han.hero.project.service.PostService;
import com.han.hero.project.vo.req.PostAddReqVo;
import com.han.hero.project.vo.req.PostListReqVo;
import com.han.hero.project.vo.req.PostUpdateReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 职位Controller
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/list")
    public R<?> list(@RequestBody PostListReqVo vo) {
        return R.ok(postService.pageList(vo));
    }

    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "新增岗位", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<?> add(@RequestBody @Validated PostAddReqVo vo) {
        postService.add(vo);
        return R.ok();
    }

    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "修改岗位", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public R<?> update(@RequestBody @Validated PostUpdateReqVo vo) {
        postService.update(vo);
        return R.ok();
    }

    @PreAuthorize("hasAnyRole('super', 'admin')")
    @Log(title = "删除岗位", businessType = BusinessType.DELETE)
    @PostMapping("/{postId}/del")
    public R<?> del(@PathVariable("postId") Integer postId) {
        postService.del(postId);
        return R.ok();
    }


}

package com.han.hero.project.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.Post;
import com.han.hero.project.mapper.PostMapper;
import com.han.hero.project.vo.req.PostAddReqVo;
import com.han.hero.project.vo.req.PostListReqVo;
import com.han.hero.project.vo.req.PostUpdateReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public PageInfo<Post> pageList(PostListReqVo vo) {
        return PageHelper.startPage(vo).setOrderBy("orderNum").doSelectPageInfo(() -> postMapper.list(vo));
    }

    public void add(PostAddReqVo vo) {
        Post post = new Post();
        post.setPostCode(vo.getPostCode());
        post.setPostName(vo.getPostName());
        post.setOrderNum(vo.getOrderNum());
        post.setDelFlag(vo.getState());
        post.setCreateBy(SecurityUtil.getUserId());
        postMapper.add(post);
    }

    public void update(PostUpdateReqVo vo) {
        Post post = new Post();
        post.setId(vo.getId());
        post.setPostCode(vo.getPostCode());
        post.setPostName(vo.getPostName());
        post.setOrderNum(vo.getOrderNum());
        post.setDelFlag(vo.getState());
        post.setUpdateBy(SecurityUtil.getUserId());
        postMapper.update(post);
    }

    public void del(Integer postId) {
        postMapper.del(postId);
    }
}

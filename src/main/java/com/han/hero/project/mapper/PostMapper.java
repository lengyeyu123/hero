package com.han.hero.project.mapper;

import com.han.hero.project.domain.Post;
import com.han.hero.project.vo.req.PostListReqVo;

import java.util.List;

public interface PostMapper {

    List<Post> list(PostListReqVo vo);

    void add(Post post);

    void update(Post post);

    void del(Integer postId);
}

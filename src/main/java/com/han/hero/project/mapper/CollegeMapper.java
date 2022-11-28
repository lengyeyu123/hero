package com.han.hero.project.mapper;

import com.han.hero.project.domain.College;
import com.han.hero.project.vo.req.CollegeReqVo;

import java.util.List;

public interface CollegeMapper {

    List<College> list(CollegeReqVo vo);

    void update(CollegeReqVo vo);

    void add(CollegeReqVo vo);
}

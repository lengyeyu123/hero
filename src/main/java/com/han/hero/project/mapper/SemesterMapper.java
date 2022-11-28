package com.han.hero.project.mapper;

import com.han.hero.project.domain.Semester;
import com.han.hero.project.vo.req.UpdateSemesterReqVo;

import java.util.List;

public interface SemesterMapper {

    List<Semester> list(Semester semester);

    void updateStatus(UpdateSemesterReqVo vo);
}

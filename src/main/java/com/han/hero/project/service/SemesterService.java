package com.han.hero.project.service;

import com.han.hero.project.domain.Semester;
import com.han.hero.project.mapper.SemesterMapper;
import com.han.hero.project.vo.req.UpdateSemesterReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    @Autowired
    private SemesterMapper semesterMapper;

    public List<Semester> list(Semester semester) {
        return semesterMapper.list(semester);
    }


    public void updateStatus(UpdateSemesterReqVo vo) {
        semesterMapper.updateStatus(vo);
    }
}

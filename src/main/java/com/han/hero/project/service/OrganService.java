package com.han.hero.project.service;

import com.han.hero.framework.annotation.DS;
import com.han.hero.project.domain.Organ;
import com.han.hero.project.mapper.OrganMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganService {

    @Autowired
    private OrganMapper organMapper;

    @DS(value = "#dbName")
    public List<Organ> all(String dbName) {
        return organMapper.all();
    }
}

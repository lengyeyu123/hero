package com.han.hero.project.service;

import com.han.hero.project.domain.OperLog;
import com.han.hero.project.mapper.OperLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 */
@Service
public class OperLogService {

    @Autowired
    private OperLogMapper operLogMapper;


    public void insertOperLog(OperLog operLog) {
        operLogMapper.insertOperLog(operLog);
    }
}

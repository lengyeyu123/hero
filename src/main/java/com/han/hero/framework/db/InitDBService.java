package com.han.hero.framework.db;

import com.han.hero.project.mapper.InitDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitDBService {

    @Autowired
    private InitDBMapper initDBMapper;

    public List<?> checkDbExist(String dbName) {
        return initDBMapper.checkDbExist(dbName);
    }

    public void createDB(String dbName) {
        initDBMapper.createDB(dbName);
    }
}

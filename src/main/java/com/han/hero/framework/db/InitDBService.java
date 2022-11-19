package com.han.hero.framework.db;

import com.han.hero.framework.annotation.DS;
import com.han.hero.project.domain.Organ;
import com.han.hero.project.domain.Super;
import com.han.hero.project.domain.User;
import com.han.hero.project.mapper.InitDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitDBService {

    @Autowired
    private InitDBMapper initDBMapper;

    public List<?> checkDBExist(String dbName) {
        return initDBMapper.checkDBExist(dbName);
    }

    public void createDB(String sql) {
        initDBMapper.createDB(sql);
    }

    public List<?> checkTableExist(String dbName, String tableName, String sysobjectsFullTableName) {
        return initDBMapper.checkTableExist(dbName, tableName, sysobjectsFullTableName);
    }

    @DS(value = "#dbName")
    public void createTable(String dbName, String superTableSql) {
        initDBMapper.createTable(superTableSql);
    }

    @DS(value = "#dbName")
    public void batchInsertSuper(String dbName, List<Super> superList) {
        initDBMapper.batchInsertSuper(superList);
    }

    @DS(value = "#dbName")
    public void batchInsertOrgan(String dbName, List<Organ> organList) {
        initDBMapper.batchInsertOrgan(organList);
    }

    @DS(value = "#dbName")
    public void batchInsertUser(String dbName, List<User> userList) {
        initDBMapper.batchInsertUser(userList);
    }
}

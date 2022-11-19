package com.han.hero.framework.db;

import com.han.hero.framework.annotation.DS;
import com.han.hero.project.domain.*;
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

    public void createDB(String dbName) {
        initDBMapper.createDB(dbName);
    }

    public List<?> checkTableExist(String dbName, String tableName) {
        return initDBMapper.checkTableExist(dbName, tableName);
    }

    @DS(value = "#dbName")
    public void createTable(String dbName, String superTableSql) {
        initDBMapper.createTable(superTableSql);
    }

    @DS(value = "#dbName")
    public void batchInsertSuper(String dbName, List<User> superList) {
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

    @DS(value = "#dbName")
    public void batchInsertRole(String dbName, List<Role> roleList) {
        initDBMapper.batchInsertRole(roleList);
    }

    @DS(value = "#dbName")
    public void batchInsertMenu(String dbName, List<Menu> menuList) {
        initDBMapper.batchInsertMenu(menuList);
    }

    @DS(value = "#dbName")
    public void batchInsertUserRole(String dbName, List<UserRole> userRoleList) {
        initDBMapper.batchInsertUserRole(userRoleList);
    }

    @DS(value = "#dbName")
    public void batchInsertRoleMenu(String dbName, List<RoleMenu> roleMenuList) {
        initDBMapper.batchInsertRoleMenu(roleMenuList);
    }

    @DS(value = "#dbName")
    public void batchInsertSemester(String dbName, List<Semester> semesterList) {
        initDBMapper.batchInsertSemester(semesterList);
    }
}

package com.han.hero.project.mapper;

import com.han.hero.project.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InitDBMapper {

    List<Map<String, Object>> checkDBExist(@Param("dbName") String dbName);

    void createDB(@Param("dbName") String dbName);

    List<Map<String, Object>> checkTableExist(@Param("dbName") String dbName, @Param("tableName") String tableName);

    void createTable(String superTableSql);

    void batchInsertSuper(@Param("superList") List<User> superList);

    void batchInsertOrgan(@Param("organList") List<Organ> organList);

    void batchInsertUser(@Param("userList") List<User> userList);

    void batchInsertRole(@Param("roleList") List<Role> roleList);

    void batchInsertMenu(@Param("menuList") List<Menu> menuList);

    void batchInsertUserRole(@Param("userRoleList") List<UserRole> userRoleList);

    void batchInsertRoleMenu(@Param("roleMenuList") List<RoleMenu> roleMenuList);

    void batchInsertSemester(@Param("semesterList") List<Semester> semesterList);

}

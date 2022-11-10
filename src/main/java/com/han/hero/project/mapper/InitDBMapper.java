package com.han.hero.project.mapper;

import com.han.hero.project.domain.Organ;
import com.han.hero.project.domain.Super;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InitDBMapper {

    List<Map<String, Object>> checkDBExist(@Param("dbName") String dbName);

    void createDB(@Param("dbName") String dbName);

    List<Map<String, Object>> checkTableExist(@Param("dbName") String dbName, @Param("tableName") String tableName);

    void createTable(String superTableSql);

    void batchInsertSuper(@Param("superList") List<Super> superList);

    void batchInsertOrgan(@Param("organList") List<Organ> organList);

}

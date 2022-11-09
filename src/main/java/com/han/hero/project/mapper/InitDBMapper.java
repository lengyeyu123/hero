package com.han.hero.project.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InitDBMapper {

    @MapKey("SCHEMA_NAME")
    List<Map<String, Object>> checkDbExist(@Param("dbName") String dbName);

    void createDB(@Param("dbName") String dbName);

}

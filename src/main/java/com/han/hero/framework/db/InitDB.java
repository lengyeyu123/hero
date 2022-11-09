package com.han.hero.framework.db;

import com.zaxxer.hikari.HikariConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Order(value = 1)
public class InitDB implements ApplicationRunner {

    @Autowired
    private HikariConfig hikariConfig;

    @Autowired
    private InitDBService initDBService;

    @Override
    public void run(ApplicationArguments args) {

        String jdbcUrl = hikariConfig.getJdbcUrl();
        String dbName = StringUtils.substringBeforeLast(StringUtils.substringAfterLast(jdbcUrl, "/"), "?");

        // 1. 检查数据库是否存在 不存在则创建数据库
        List<?> list = initDBService.checkDbExist(dbName);
        if (list.isEmpty()) {
            // 创建数据库
            initDBService.createDB(dbName);

        }
        // 2. 检查表是否存在 不存在则创建表 并插入基础数据
        checkTableExist();
        log.info("==========数据库初始化完成=============");
    }

    private void checkDBExist() {


    }

    private void checkTableExist() {


    }


}

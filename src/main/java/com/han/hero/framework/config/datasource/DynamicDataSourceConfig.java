package com.han.hero.framework.config.datasource;

import com.han.hero.common.constants.DataSourceConstants;
import com.han.hero.common.util.SpringUtil;
import com.han.hero.framework.config.properties.DataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
public class DynamicDataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    public DataSource createDataSource(String dbName) {
        Class<? extends DataSource> dataSourceType = null;
        try {
            dataSourceType = (Class<? extends DataSource>) Class.forName(dataSourceProperties.getType());
        } catch (ClassNotFoundException e) {
            log.error("DataSourceType 类名错误", e);
        }
        if (StringUtils.isBlank(dbName)) {
            // 默认master数据源
            return DataSourceBuilder.create()
                    .type(dataSourceType)
                    .driverClassName(dataSourceProperties.getDriverClassName())
                    .url(dataSourceProperties.getUrl())
                    .username(dataSourceProperties.getUsername())
                    .password(dataSourceProperties.getPassword())
                    .build();

        } else {
            return DataSourceBuilder.create()
                    .type(dataSourceType)
                    .driverClassName(dataSourceProperties.getDriverClassName())
                    .url(dataSourceProperties.getUrl().replaceAll("DatabaseName=.*?;", "DatabaseName=" + dbName + ";"))
                    .username(dataSourceProperties.getUsername())
                    .password(dataSourceProperties.getPassword())
                    .build();
        }
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        DataSource baseDataSource = createDataSource(null);
        // 设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = dynamicDataSource.getDataSourceMap();
        dataSourceMap.put(DataSourceConstants.DS_KEY_BASE, baseDataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(baseDataSource);
        return dynamicDataSource;
    }

    public void addDataSource(String dbName, boolean defaultFlag) {
        DynamicDataSource dataSource = SpringUtil.getBean("dynamicDataSource");
        DataSource newDataSource = createDataSource(dbName);
        Map<Object, Object> dataSourceMap = dataSource.getDataSourceMap();
        dataSourceMap.put(dbName, newDataSource);
        dataSource.setTargetDataSources(dataSourceMap);
        if (defaultFlag) {
            dataSource.setDefaultTargetDataSource(newDataSource);
        }
    }

}

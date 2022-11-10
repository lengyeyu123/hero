package com.han.hero.framework.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet(); // 必须添加该句，否则新添加数据源无法识别到
    }
}

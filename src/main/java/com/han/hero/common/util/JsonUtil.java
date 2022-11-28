package com.han.hero.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.hero.common.constants.DateConstants;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 时间格式 时区
        objectMapper.setDateFormat(new SimpleDateFormat(DateConstants.yyyyMMddHHmmss));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @SneakyThrows
    public static String toJson(Object bean) {
        return objectMapper.writeValueAsString(bean);
    }

    @SneakyThrows
    public static <T> T toBean(String jsonStr, Class<T> beanType) {
        return objectMapper.readValue(jsonStr, beanType);
    }

    @SneakyThrows
    public static <T> T toBean(String jsonStr, TypeReference<T> typeReference) {
        return objectMapper.readValue(jsonStr, typeReference);
    }

}

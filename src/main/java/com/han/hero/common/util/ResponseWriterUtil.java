package com.han.hero.common.util;

import com.han.hero.common.web.domain.R;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;

public class ResponseWriterUtil {

    @SneakyThrows
    public static void write401(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JsonUtil.toJson(new R<>()));
    }

    @SneakyThrows
    public static void write403(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(JsonUtil.toJson(new R<>()));
    }


}

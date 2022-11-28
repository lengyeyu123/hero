package com.han.hero.common.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;


public class ResponseWriterUtil {

    @SneakyThrows
    public static void write401(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().flush();
    }

    @SneakyThrows
    public static void write403(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().flush();
    }

    @SneakyThrows
    public static void write(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(json);
    }


}

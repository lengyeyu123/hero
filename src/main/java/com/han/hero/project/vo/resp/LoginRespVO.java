package com.han.hero.project.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespVO {

    private Integer userId;

    private String username;

    private String realName;

    private String avatar;

    private String desc;

    private String password;

    private String token;

    private String homePath;

    private List<Map<String, String>> roles = new ArrayList<>();

}

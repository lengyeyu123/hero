package com.han.hero.project.service;

import com.han.hero.framework.annotation.DS;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.User;
import com.han.hero.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectById(Integer userId) {
        return userMapper.selectById(userId);
    }

    public Set<Menu> getUserPermissions(Integer userId) {
        return userMapper.getUserPermissions(userId);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    /**
     * 验证用户名是否唯一
     */
    public boolean validateUserName(String userName) {
        return userMapper.countByUserName(userName) == 0;
    }

    @DS(value = "#dbName")
    public User selectByUserName(String dbName, String userName) {
        return userMapper.selectByUserName(userName);
    }
}

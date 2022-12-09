package com.han.hero.project.service;

import com.github.pagehelper.PageInfo;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.User;
import com.han.hero.project.mapper.UserMapper;
import com.han.hero.project.vo.req.UserPageListReqVo;
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

    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    public PageInfo<?> pageList(UserPageListReqVo vo) {
        // LoginUser loginUser = SecurityUtil.getLoginUser();
        // if (loginUser.getUser().getId() == 1) {
        //     // super 管理员 可以查看所有用户
        // }else {
        //     // 院系管理员 只能查看本院系的用户 其他角色不应该能查看到用户管理菜单
        //
        // }
        return null;
    }
}

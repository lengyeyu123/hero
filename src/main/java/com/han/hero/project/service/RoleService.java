package com.han.hero.project.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.RoleMenu;
import com.han.hero.project.domain.UserRole;
import com.han.hero.project.mapper.MenuMapper;
import com.han.hero.project.mapper.RoleMapper;
import com.han.hero.project.mapper.UserMapper;
import com.han.hero.project.vo.req.RoleAddReqVo;
import com.han.hero.project.vo.req.RoleListReqVo;
import com.han.hero.project.vo.req.RoleUpdateReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    public List<Role> list(RoleListReqVo vo) {
        Page<Object> page = PageHelper.startPage(vo);
        page.setOrderBy("orderNum");
        return roleMapper.list(vo);
    }

    public PageInfo<?> pageList(RoleListReqVo vo) {
        return PageHelper.startPage(vo)
                .setOrderBy("orderNum")
                .doSelectPageInfo(() -> roleMapper.list(vo));
    }

    public void add(RoleAddReqVo vo) {
        Role role = new Role();
        role.setName(vo.getName());
        role.setOrderNum(vo.getOrderNum());
        role.setDelFlag(vo.getDelFlag());
        role.setCreateBy(SecurityUtil.getUserId());
        roleMapper.add(role);
    }

    public void update(RoleUpdateReqVo vo) {
        Role role = new Role();
        role.setId(vo.getId());
        role.setName(vo.getName());
        role.setOrderNum(vo.getOrderNum());
        role.setDelFlag(vo.getDelFlag());
        role.setRemark(vo.getRemark());
        role.setUpdateBy(SecurityUtil.getUserId());
        roleMapper.update(role);
    }

    public void del(Integer roleId) {
        // ???????????????????????????????????? ????????????????????????
        List<UserRole> userRoleList = userMapper.selectUserRoleByRoleId(roleId);
        List<RoleMenu> roleMenuList = menuMapper.selectRoleMenuByRoleId(roleId);
        if (!userRoleList.isEmpty() || !roleMenuList.isEmpty()) {
            throw new ServiceException(ResultStatus.COMMON_DATA_HAS_USED);
        }
        roleMapper.del(roleId);
    }

    public List<Role> all() {
        return roleMapper.all();
    }
}

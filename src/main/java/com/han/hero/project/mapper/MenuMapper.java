package com.han.hero.project.mapper;

import com.han.hero.project.domain.Menu;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.RoleMenu;
import com.han.hero.project.vo.req.MenuAddReqVo;
import com.han.hero.project.vo.req.MenuListReqVo;

import java.util.List;

public interface MenuMapper {

    List<RoleMenu> selectRoleMenuByRoleId(Integer roleId);

    Menu selectByMenuId(Integer parentId);

    void add(MenuAddReqVo vo);

    List<Menu> selectByParentMenuId(Integer menuId);

    void del(Integer menuId);

    void update(Menu menu);

    List<Menu> list(MenuListReqVo vo);

    List<String> allPerms();

    List<String> selectPermsByRoles(List<Role> roles);

    List<Menu> selectMenuTreeAll();

    List<Menu> selectMenuTreeByUserId(Integer id);
}

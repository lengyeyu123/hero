package com.han.hero.project.service;

import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.Menu;
import com.han.hero.project.mapper.MenuMapper;
import com.han.hero.project.vo.req.MenuAddReqVo;
import com.han.hero.project.vo.req.MenuListReqVo;
import com.han.hero.project.vo.req.MenuUpdateReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public void add(MenuAddReqVo vo) {
        Menu menu = new Menu();
        menu.setMenuName(vo.getMenuName());
        menu.setParentId(vo.getParentId());
        if (vo.getParentId() != null) {
            Menu parentMenu = menuMapper.selectByMenuId(vo.getParentId());
            if (parentMenu != null) {
                menu.setParentName(parentMenu.getMenuName());
            }
        }
        menu.setPath(vo.getPath());
        menu.setComponent(vo.getComponent());
        menu.setMenuType(vo.getMenuType());
        menu.setDelFlag(vo.getState());
        menu.setPerms(vo.getPerms());
        menu.setIcon(vo.getIcon());
        menu.setCreateBy(SecurityUtil.getUserId());
        menuMapper.add(vo);
    }

    public void del(Integer menuId) {
        // 菜单时候包含子菜单
        List<Menu> parentMenuList = menuMapper.selectByParentMenuId(menuId);
        if (!parentMenuList.isEmpty()) {
            throw new ServiceException(ResultStatus.COMMON_DATA_HAS_USED);
        }
        menuMapper.del(menuId);
    }

    public void update(MenuUpdateReqVo vo) {
        Menu menu = new Menu();
        menu.setId(vo.getMuenId());
        menu.setMenuName(vo.getMenuName());
        if (vo.getParentId() != null) {
            Menu parentMenu = menuMapper.selectByMenuId(vo.getParentId());
            if (parentMenu != null) {
                menu.setParentId(parentMenu.getId());
                menu.setParentName(parentMenu.getMenuName());
            }
        }
        menu.setOrderNum(vo.getOrderNum());
        menu.setPath(vo.getPath());
        menu.setComponent(vo.getComponent());
        menu.setMenuType(vo.getMenuType());
        menu.setDelFlag(vo.getState());
        menu.setPerms(vo.getPerms());
        menu.setIcon(vo.getIcon());
        menu.setUpdateBy(SecurityUtil.getUserId());
        menuMapper.update(menu);
    }

    public List<Menu> list(MenuListReqVo vo) {
        List<Menu> list = menuMapper.list(vo);
        list = list.stream().sorted(Comparator.comparingInt(Menu::getParentId)).collect(Collectors.toList());
        if (!list.isEmpty()) {
            Integer parentId = list.get(0).getParentId();
            treeData(list, parentId);
        }
        return list;
    }

    private void treeData(List<Menu> list, Integer parentId) {
        List<Menu> topMenuList = list.stream()
                .filter(menu -> Objects.equals(menu.getParentId(), parentId))
                .sorted(Comparator.comparingInt(Menu::getOrderNum))
                .collect(Collectors.toList());
        for (Menu topMenu : topMenuList) {
            List<Menu> childMenuList = list.stream()
                    .filter(menu -> Objects.equals(menu.getParentId(), topMenu.getId()))
                    .sorted(Comparator.comparingInt(Menu::getOrderNum))
                    .collect(Collectors.toList());
            topMenu.setChildren(childMenuList);
            list.removeAll(childMenuList);
        }
        for (Menu menu : topMenuList) {
            List<Menu> menuChildren = menu.getChildren();
            if (!menuChildren.isEmpty()) {
                for (Menu menuChild : menuChildren) {
                    treeData(list, menuChild.getId());
                }
            }
        }
    }
}

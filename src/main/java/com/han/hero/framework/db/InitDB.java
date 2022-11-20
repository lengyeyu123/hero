package com.han.hero.framework.db;

import com.han.hero.common.constants.DataSourceConstants;
import com.han.hero.common.enums.OrganType;
import com.han.hero.framework.config.datasource.DynamicDataSourceConfig;
import com.han.hero.framework.config.properties.HeroProperties;
import com.han.hero.project.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 注意事项
 * 1. 所有数据库中的t_user表均添加了super2022用户 用户id为1 用户名为super2022 密码为super2022 默认用户名为super2022的用户拥有所有权限
 * 2. 演示机构code为 000000
 */
@Slf4j
@Component
@Order(value = 1)
public class InitDB implements ApplicationRunner {

    @Autowired
    private HeroProperties heroProperties;

    @Autowired
    private InitDBService initDBService;

    @Autowired
    private DynamicDataSourceConfig dynamicDataSourceConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {

        String dbName = heroProperties.getDbName();

        // 1. 检查数据库是否存在 不存在则创建数据库 创建完成数据库添加数据源
        List<?> list = initDBService.checkDBExist(dbName);
        if (list.isEmpty()) {
            // 创建数据库
            initDBService.createDB(dbName);
        }
        // 添加数据源
        dynamicDataSourceConfig.addDataSource(dbName, false);
        // 2. 检查主库(hero)中表是否存在 不存在则创建表 并插入基础数据
        checkTableExist();
        log.info("==========数据库初始化完成=============");
    }

    /**
     * hero 基础数据库包含的表 t_user t_menu t_organ t_dict_type t_dict_data t_semester
     */
    private void checkTableExist() {
        String dbName = heroProperties.getDbName();
        // hero 中的表 t_user t_organ t_menu

        // ---- t_user表是否存在
        List<?> userTableList = initDBService.checkTableExist(DataSourceConstants.DS_KEY_BASE, "t_user");
        if (userTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.UserTableSql);
            List<User> superList = new ArrayList<>();
            User superUser = new User();
            superUser.setId(1);
            superUser.setUserName("super2022");
            superUser.setPassword(passwordEncoder.encode("super2022"));
            superUser.setRemark("super man set sys config");
            superList.add(superUser);
            initDBService.batchInsertUser(dbName, superList);
        }

        // ---- t_organ是否存在
        List<?> organTableList = initDBService.checkTableExist(dbName, "t_organ");
        if (organTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.OrganTableSql);
            // 若非演示模式 不创建机构数据库
            if (heroProperties.getDemo()) {
                List<Organ> organList = new ArrayList<>();
                Organ organ = new Organ();
                // 演示机构
                organ.setCode("000000");
                organ.setName("demo");
                organ.setOrganType(OrganType.UNIVERSITY);
                organ.setAddress("my");
                organ.setEmail("my@qq.com");
                organ.setLinkman("my");
                organ.setLinkmanPhone("phone");
                organ.setRemark("remark");
                organList.add(organ);
                initDBService.batchInsertOrgan(dbName, organList);
                for (Organ o : organList) {
                    initOrganDB(o);
                }
            }
        }

        // ---- t_dict_type是否存在
        List<?> dictTypeTableList = initDBService.checkTableExist(dbName, "t_dict_type");
        if (dictTypeTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.DictTypeTableSql);
        }

        // ---- t_dict_data是否存在
        List<?> dictDataTableList = initDBService.checkTableExist(dbName, "t_dict_data");
        if (dictDataTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.DictDataTableSql);
        }

        // ---- t_menu是否存在
        List<?> menuTableList = initDBService.checkTableExist(dbName, "t_menu");
        if (menuTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.MenuTableSql);
            List<Menu> menuList = new ArrayList<>();
            initHeroDbAllMenu(menuList);
            initDBService.batchInsertMenu(dbName, menuList);
        }

        // ---- t_semester是否存在
        List<?> semesterTableList = initDBService.checkTableExist(dbName, "t_semester");
        if (semesterTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.SemesterTableSql);
            // 添加当前学期
            List<Semester> semesterList = new ArrayList<>();
            Semester curSemester = Semester.getCurSemester();
            semesterList.add(curSemester);
            initDBService.batchInsertSemester(dbName, semesterList);
        }
    }


    /**
     * 初始化机构数据库
     * 机构数据库包含的表为
     * 基础：t_user t_role t_menu t_user_role t_role_menu
     * 业务：t_subject t_exam t_exam_que t_exam_stu t_exam_tea t_exam_answer ……
     */
    public void initOrganDB(@NotNull Organ organ) {
        String dbName = "organ_" + organ.getCode();
        // 1. 检查数据库是否存在
        List<?> list = initDBService.checkDBExist(dbName);
        if (list.isEmpty()) {
            // 2. 创建数据库
            initDBService.createDB(dbName);
        }

        // 3. 添加数据源
        dynamicDataSourceConfig.addDataSource(dbName, false);
        // 4. t_user 检查表是否存在 不存在则创建表并插入基础数据
        // ---- t_user是否存在
        List<User> userList = new ArrayList<>();
        List<?> userTableList = initDBService.checkTableExist(dbName, "t_user");
        if (userTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.UserTableSql);

            // 插入基础数据
            User superUser = new User();
            superUser.setId(1);
            superUser.setUserName("super2022");
            superUser.setPassword(passwordEncoder.encode("super2022"));
            superUser.setRemark("I am superman");
            userList.add(superUser);
            User user = new User();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("admin2022"));
            user.setRemark("i am admin");
            userList.add(user);
            initDBService.batchInsertUser(dbName, userList);
        }

        // // ---- t_role是否存在
        List<Role> roleList = new ArrayList<>();
        List<?> roleTableList = initDBService.checkTableExist(dbName, "t_role");
        if (roleTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.RoleTableSql);
            Role superRole = new Role();
            superRole.setRoleCode("super");
            superRole.setRoleName("超级管理员");
            roleList.add(superRole);
            initDBService.batchInsertRole(dbName, roleList);
        }

        // ---- t_menu是否存在
        List<Menu> menuList = new ArrayList<>();
        List<?> menuTableList = initDBService.checkTableExist(dbName, "t_menu");
        if (menuTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.MenuTableSql);
            initOrganAllMenu(menuList);
            initDBService.batchInsertMenu(dbName, menuList);
        }

        // ---- t_user_role是否存在
        List<UserRole> userRoleList = new ArrayList<>();
        List<?> userRoleTableList = initDBService.checkTableExist(dbName, "t_user_role");
        if (userRoleTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.UserRoleTableSql);
            // for (User user : userList) {
            //     for (Role role : roleList) {
            //         UserRole userRole = new UserRole();
            //         userRole.setUserId(user.getId());
            //         userRole.setRoleId(role.getId());
            //         userRoleList.add(userRole);
            //     }
            // }
            // initDBService.batchInsertUserRole(dbName, userRoleList);
        }

        // ---- t_role_menu是否存在
        List<RoleMenu> roleMenuList = new ArrayList<>();
        List<?> roleMenuTableList = initDBService.checkTableExist(dbName, "t_role_menu");
        if (roleMenuTableList.isEmpty()) {
            initDBService.createTable(dbName, InitSQL.RoleMenuTableSql);
            // for (Role role : roleList) {
            //     for (Menu menu : menuList) {
            //         RoleMenu roleMenu = new RoleMenu();
            //         roleMenu.setRoleId(role.getId());
            //         roleMenu.setMenuId(menu.getId());
            //         roleMenuList.add(roleMenu);
            //     }
            // }
            // initDBService.batchInsertRoleMenu(dbName, roleMenuList);
        }

    }

    /**
     * 主库菜单
     *
     * @param menuList
     */
    private void initHeroDbAllMenu(List<Menu> menuList) {
        // menuList.add(new Menu().setMenuType(MenuType.M));
        // menuList.add(new Menu().setMenuType(MenuType.M));
        // menuList.add(new Menu().setMenuType(MenuType.M));
        // menuList.add(new Menu().setMenuType(MenuType.M));
    }

    /**
     * 机构所有的菜单
     */
    public void initOrganAllMenu(List<Menu> menuList) {
        // menuList.add(new Menu().setMenuType(MenuType.M));
        // menuList.add(new Menu().setMenuType(MenuType.M));
        // menuList.add(new Menu().setMenuType(MenuType.M));
        // menuList.add(new Menu().setMenuType(MenuType.M));
    }

}

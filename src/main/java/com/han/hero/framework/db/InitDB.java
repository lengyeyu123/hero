package com.han.hero.framework.db;

import cn.hutool.core.io.FileUtil;
import com.han.hero.common.enums.OrganType;
import com.han.hero.framework.config.datasource.DynamicDataSourceConfig;
import com.han.hero.framework.config.properties.HeroProperties;
import com.han.hero.project.domain.Organ;
import com.han.hero.project.domain.Role;
import com.han.hero.project.domain.Super;
import com.han.hero.project.domain.User;
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
            // 检查数据库文件存放路径是否存在
            FileUtil.mkdir(heroProperties.getDbFilePathPrefix());
            initDBService.createDB(generateCreateDbSql(dbName));
        }
        // 添加数据源
        dynamicDataSourceConfig.addDataSource(dbName, false);
        // 2. 检查主库(hero)中表是否存在 不存在则创建表 并插入基础数据
        checkTableExist();
        log.info("==========数据库初始化完成=============");
    }

    private String generateCreateDbSql(String dbName) {
        return "CREATE DATABASE " + dbName + " ON PRIMARY (" +
                "NAME=" + dbName + "_DATA," +
                "FILENAME='" + heroProperties.getDbFilePathPrefix() + dbName + "_DATA.mdf'," +
                "SIZE=5MB," +
                "MAXSIZE=UNLIMITED," +
                "FILEGROWTH=65536KB" +
                ")" +
                "LOG ON(NAME=" + dbName + "_LOG," +
                "FILENAME='" + heroProperties.getDbFilePathPrefix() + dbName + "_LOG.ldf'," +
                "SIZE=2MB," +
                "MAXSIZE=2048GB," +
                "FILEGROWTH=65536KB)";
    }

    private void checkTableExist() {
        String dbName = heroProperties.getDbName();

        // ---- super表是否存在
        List<?> superTableList = initDBService.checkTableExist(dbName, "t_super", dbName + ".dbo.sysobjects");
        if (superTableList.isEmpty()) {
            String superTableSql = "CREATE TABLE [t_super](" +
                    "[id] [int] IDENTITY(1,1) NOT NULL," +
                    "[userName] [nvarchar](100) NOT NULL," +
                    "[password] [nvarchar](100) NOT NULL," +
                    "[createBy] [int] NULL," +
                    "[updateBy] [int] NULL," +
                    "[createTime] [datetime2](7) NULL," +
                    "[updateTime] [datetime2](7) NULL," +
                    "[remark] [nvarchar](200) NULL," +
                    "[delFlag] [tinyint] NOT NULL DEFAULT 0)";
            initDBService.createTable(dbName, superTableSql);
            List<Super> superList = new ArrayList<>();
            Super superUser = new Super();
            superUser.setUserName("super");
            superUser.setPassword(passwordEncoder.encode("super"));
            superUser.setRemark("super man set sys config");
            superList.add(superUser);
            initDBService.batchInsertSuper(dbName, superList);
        }

        // ---- 机构表是否存在
        List<?> organTableList = initDBService.checkTableExist(dbName, "t_organ", dbName + ".dbo.sysobjects");
        if (organTableList.isEmpty()) {
            String organTableSql = "CREATE TABLE [t_organ](" +
                    "[id] [int] IDENTITY(1,1) NOT NULL," +
                    "[name] [nvarchar](100) NOT NULL," +
                    "[organType] [tinyint] NOT NULL," +
                    "[email] [nvarchar](100) NULL," +
                    "[address] [nvarchar](200) NULL," +
                    "[linkman] [nvarchar](50) NULL," +
                    "[linkmanPhone] [nvarchar](11) NULL," +
                    "[createBy] [int] NULL," +
                    "[updateBy] [int] NULL," +
                    "[createTime] [datetime2](7) NULL," +
                    "[updateTime] [datetime2](7) NULL," +
                    "[remark] [nvarchar](200) NULL," +
                    "[delFlag] [tinyint] NOT NULL DEFAULT 0)";
            initDBService.createTable(dbName, organTableSql);
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

    // 初始化机构数据库
    public void initOrganDB(@NotNull Organ organ) {
        String dbName = "organ_" + organ.getCode();
        // 1. 检查数据库是否存在
        List<?> list = initDBService.checkDBExist(dbName);
        if (list.isEmpty()) {
            // 2. 创建数据库
            FileUtil.mkdir(heroProperties.getDbFilePathPrefix());
            initDBService.createDB(generateCreateDbSql(dbName));
        }

        // 3. 添加数据源
        dynamicDataSourceConfig.addDataSource(dbName, false);
        // 4. 检查表是否存在 不存在则创建表并插入基础数据
        List<?> userTableList = initDBService.checkTableExist(dbName, "t_user", dbName + ".dbo.sysobjects");
        if (userTableList.isEmpty()) {
            String userTableSql = "CREATE TABLE [t_user](" +
                    "[id] [int] IDENTITY(1,1) NOT NULL," +
                    "[userName] [nvarchar](100) NOT NULL," +
                    "[password] [nvarchar](100) NOT NULL," +
                    "[createBy] [int] NULL," +
                    "[updateBy] [int] NULL," +
                    "[createTime] [datetime2](7) NULL," +
                    "[updateTime] [datetime2](7) NULL," +
                    "[remark] [nvarchar](200) NULL," +
                    "[delFlag] [tinyint] NOT NULL DEFAULT 0)";
            initDBService.createTable(dbName, userTableSql);
            // 插入基础数据
            List<User> userList = new ArrayList<>();
            User user = new User();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRemark("i am admin");
            userList.add(user);
            initDBService.batchInsertUser(dbName, userList);
        }

        List<?> roleTableList = initDBService.checkTableExist(dbName, "t_role", dbName + ".dbo.sysobjects");
        if (roleTableList.isEmpty()) {
            String roleTableSql = "CREATE TABLE [t_role](" +
                    "[id] [int] IDENTITY(1,1) NOT NULL," +
                    "[roleCode] [nvarchar](50) NOT NULL," +
                    "[roleName] [nvarchar](50) NOT NULL," +
                    "[orderNum] [int] NULL," +
                    "[createBy] [int] NULL," +
                    "[updateBy] [int] NULL," +
                    "[createTime] [datetime2](7) NULL," +
                    "[updateTime] [datetime2](7) NULL," +
                    "[remark] [nvarchar](200) NULL," +
                    "[delFlag] [tinyint] NOT NULL DEFAULT 0)";
            initDBService.createTable(dbName, roleTableSql);
            // 插入基础数据
            List<Role> roleList = new ArrayList<>();
            Role role = new Role();
            role.setRoleCode("admin");
            role.setRoleName("管理员");
            roleList.add(role);
            initDBService.batchInsertRole(dbName, roleList);
        }

        List<?> menuTableList = initDBService.checkTableExist(dbName, "t_menu", dbName + ".dbo.sysobjects");
        if (menuTableList.isEmpty()) {
            if (menuTableList.isEmpty()) {
                String menuTableSql = "CREATE TABLE [t_role](" +
                        "[id] [int] IDENTITY(1,1) NOT NULL," +
                        "[menuCode] [nvarchar](50) NOT NULL," +
                        "[menuName] [nvarchar](50) NOT NULL," +
                        "[parentId] [int] NULL," +
                        "[parentCode] [nvarchar](50) NULL," +
                        "[parentName] [nvarchar](50) NULL," +
                        "[orderNum] [int] NULL," +
                        "[path] [nvarchar](200) NULL," +
                        "[component] [nvarchar](50) NULL," +
                        "[menuType] [varchar](1) NULL," +
                        "[perms] [nvarchar](50) NULL," +
                        "[icon] [nvarchar](50) NULL," +
                        "[createBy] [int] NULL," +
                        "[updateBy] [int] NULL," +
                        "[createTime] [datetime2](7) NULL," +
                        "[updateTime] [datetime2](7) NULL," +
                        "[remark] [nvarchar](200) NULL," +
                        "[delFlag] [tinyint] NOT NULL DEFAULT 0)";
                initDBService.createTable(dbName, menuTableSql);
                // 插入基础数据


            }
        }

    }

}

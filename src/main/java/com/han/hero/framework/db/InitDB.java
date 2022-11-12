package com.han.hero.framework.db;

import com.han.hero.common.enums.OrganType;
import com.han.hero.framework.config.datasource.DynamicDataSourceConfig;
import com.han.hero.framework.config.properties.HeroProperties;
import com.han.hero.project.domain.Organ;
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
            initDBService.createDB(dbName);
        }
        // 添加数据源
        dynamicDataSourceConfig.addDataSource(dbName, false);
        // 2. 检查主库(hero)中表是否存在 不存在则创建表 并插入基础数据
        checkTableExist();
        log.info("==========数据库初始化完成=============");
    }

    private void checkTableExist() {
        String dbName = heroProperties.getDbName();

        // ---- super表是否存在
        List<?> superTableList = initDBService.checkTableExist(dbName, "t_super");
        if (superTableList.isEmpty()) {
            String superTableSql = "CREATE TABLE `t_super`  (" +
                    "  `id` int NOT NULL AUTO_INCREMENT," +
                    "  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                    "  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                    "  `createBy` int NULL DEFAULT NULL," +
                    "  `updateBy` int NULL DEFAULT NULL," +
                    "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                    "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                    "  `delFlag` tinyint NOT NULL DEFAULT 0," +
                    "  PRIMARY KEY (`id`) USING BTREE" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
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
        List<?> organTableList = initDBService.checkTableExist(dbName, "t_organ");
        if (organTableList.isEmpty()) {
            String organTableSql = "CREATE TABLE `t_organ`  (" +
                    "  `id` int NOT NULL AUTO_INCREMENT," +
                    "  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                    "  `organType` tinyint NOT NULL," +
                    "  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                    "  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                    "  `linkman` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                    "  `linkmanPhone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                    "  `createBy` int NULL DEFAULT NULL," +
                    "  `updateBy` int NULL DEFAULT NULL," +
                    "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                    "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                    "  `delFlag` tinyint NOT NULL DEFAULT 0," +
                    "  PRIMARY KEY (`id`) USING BTREE" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";

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
            initDBService.createDB(dbName);
        }

        // 3. 添加数据源
        dynamicDataSourceConfig.addDataSource(dbName, false);
        // 4. 检查表是否存在 不存在则创建表并插入基础数据
        List<?> userTableList = initDBService.checkTableExist(dbName, "t_user");
        if (userTableList.isEmpty()) {
            String userTableSql = "CREATE TABLE `t_user`  (" +
                    "  `id` int NOT NULL AUTO_INCREMENT," +
                    "  `userName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                    "  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                    "  `createBy` int NULL DEFAULT NULL," +
                    "  `updateBy` int NULL DEFAULT NULL," +
                    "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                    "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                    "  `delFlag` tinyint NOT NULL DEFAULT 0," +
                    "  PRIMARY KEY (`id`) USING BTREE" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
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


    }

}

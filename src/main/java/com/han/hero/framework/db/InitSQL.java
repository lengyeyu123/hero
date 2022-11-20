package com.han.hero.framework.db;

public class InitSQL {

    public static final String UserTableSql = "CREATE TABLE `t_user`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `userName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `realName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL," +
            "  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `createBy` int NULL DEFAULT NULL," +
            "  `updateBy` int NULL DEFAULT NULL," +
            "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `updateTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP," +
            "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `delFlag` tinyint NOT NULL DEFAULT 0," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
    ;

    public static final String OrganTableSql = "CREATE TABLE `t_organ`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `type` tinyint NOT NULL," +
            "  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `linkman` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `linkmanPhone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `createBy` int NULL DEFAULT NULL," +
            "  `updateBy` int NULL DEFAULT NULL," +
            "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `updateTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP," +
            "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `delFlag` tinyint NOT NULL DEFAULT 0," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
    ;

    public static final String RoleTableSql = "CREATE TABLE `t_role`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `orderNum` int NULL DEFAULT NULL," +
            "  `createBy` int NULL DEFAULT NULL," +
            "  `updateBy` int NULL DEFAULT NULL," +
            "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `updateTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP," +
            "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `delFlag` tinyint NOT NULL DEFAULT 0," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
    ;

    public static final String MenuTableSql = "CREATE TABLE `t_menu`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `parentId` int NULL DEFAULT NULL," +
            "  `parentName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL," +
            "  `orderNum` int NULL DEFAULT NULL," +
            "  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL," +
            "  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL," +
            "  `type` tinyint NOT NULL," +
            "  `perms` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `icon`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `createBy` int NULL DEFAULT NULL," +
            "  `updateBy` int NULL DEFAULT NULL," +
            "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `updateTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP," +
            "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `delFlag` tinyint NOT NULL DEFAULT 0," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
    ;

    public static final String UserRoleTableSql = "CREATE TABLE `t_user_role`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `userId` int NOT NULL," +
            "  `roleId` int NOT NULL," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
    ;

    public static final String RoleMenuTableSql = "CREATE TABLE `t_role_menu`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `roleId` int NOT NULL," +
            "  `menuId` int NOT NULL," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
    ;

    public static final String DictTypeTableSql = "CREATE TABLE `t_dict_type`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `createBy` int NULL DEFAULT NULL," +
            "  `updateBy` int NULL DEFAULT NULL," +
            "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `updateTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP," +
            "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `delFlag` tinyint NOT NULL DEFAULT 0," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";

    public static final String DictDataTableSql = "CREATE TABLE `t_dict_data`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `orderNum` int NULL DEFAULT NULL," +
            "  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `val` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
            "  `defaultState` int NULL DEFAULT NULL," +
            "  `createBy` int NULL DEFAULT NULL," +
            "  `updateBy` int NULL DEFAULT NULL," +
            "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `updateTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP," +
            "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `delFlag` tinyint NOT NULL DEFAULT 0," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";
    ;
    public static final String SemesterTableSql = "CREATE TABLE `t_semester`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `startDate` date NOT NULL," +
            "  `endDate` date NOT NULL," +
            "  `openState` tinyint NOT NULL DEFAULT 1," +
            "  `createBy` int NULL DEFAULT NULL," +
            "  `updateBy` int NULL DEFAULT NULL," +
            "  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `updateTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP," +
            "  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
            "  `delFlag` tinyint NOT NULL DEFAULT 0," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;";

}

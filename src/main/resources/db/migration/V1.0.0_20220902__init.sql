/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : hero

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 09/11/2022 22:25:03
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`
(
    `installed_rank` int                                                           NOT NULL,
    `version`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `description`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `type`           varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `script`         text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `checksum`       int NULL DEFAULT NULL,
    `installed_by`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `installed_on`   datetime                                                      NOT NULL,
    `execution_time` int                                                           NOT NULL,
    `success`        tinyint                                                       NOT NULL,
    PRIMARY KEY (`installed_rank`) USING BTREE,
    INDEX            `flyway_schema_history_s_idx`(`success` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history`
VALUES (1, '1.0.0.20220902', 'init', 'SQL', 'V1.0.0_20220902__init.sql', -187461026, 'jkwsyjxt', '2022-09-07 15:48:51',
        117, 1);

-- ----------------------------
-- Table structure for h_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `h_dict_data`;
CREATE TABLE `h_dict_data`
(
    `dictCode`     int NOT NULL,
    `orderNum`     int NULL DEFAULT NULL,
    `dictLabel`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `dictValue`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `dictType`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `defaultState` int NULL DEFAULT NULL,
    `delFlag`      int NULL DEFAULT 0,
    `createBy`     int NULL DEFAULT NULL,
    `createTime`   datetime NULL DEFAULT NULL,
    `updateBy`     int NULL DEFAULT NULL,
    `updateTime`   datetime NULL DEFAULT NULL,
    `remark`       char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`dictCode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for h_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `h_dict_type`;
CREATE TABLE `h_dict_type`
(
    `dictId`     int NOT NULL,
    `dictName`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `dictType`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delFlag`    int NULL DEFAULT 0,
    `createBy`   int NULL DEFAULT NULL,
    `createTime` datetime NULL DEFAULT NULL,
    `updateBy`   int NULL DEFAULT NULL,
    `updateTime` datetime NULL DEFAULT NULL,
    `remark`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`dictId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_dict_type
-- ----------------------------
INSERT INTO `h_dict_type`
VALUES (1, '1', '2', 1, 3, '2022-09-09 16:51:27', NULL, NULL, '333');
INSERT INTO `h_dict_type`
VALUES (2, '23', 'sada', 1, 3, '2022-09-09 16:54:29', NULL, NULL, 'sdasd');
INSERT INTO `h_dict_type`
VALUES (13, '用户性别', 'sys_user_sex', 1, 3, '2022-09-13 14:37:57', NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (15, '1', '2', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (16, '2', '3', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (18, '4', '5', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (19, '5', '6', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (23, '9', '11', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (30, 'sd', 'sds', 1, 3, '2022-09-16 08:25:15', NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (31, '12', '33', 1, 3, '2022-09-16 10:32:16', NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (32, '11111', '22ssssssss', 0, 3, '2022-09-16 10:32:26', NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (33, '1', '334', 0, 3, '2022-09-16 10:33:10', NULL, NULL, NULL);
INSERT INTO `h_dict_type`
VALUES (34, 'ggg', 'ssss', 0, 3, '2022-09-16 10:33:33', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for h_menu
-- ----------------------------
DROP TABLE IF EXISTS `h_menu`;
CREATE TABLE `h_menu`
(
    `menuId`     int NOT NULL,
    `menuName`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `parentId`   int NULL DEFAULT NULL,
    `parentName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `orderNum`   int NULL DEFAULT NULL,
    `path`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GET:/post/list 请求路径',
    `component`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由组件',
    `menuType`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型 (M目录 C菜单 F按钮)',
    `delFlag`    int NULL DEFAULT 0,
    `perms`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限字符 *:*:*拥有所有权限 super',
    `icon`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `createBy`   int NULL DEFAULT NULL,
    `createTime` datetime NULL DEFAULT NULL,
    `updateBy`   int NULL DEFAULT NULL,
    `updateTime` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_menu
-- ----------------------------
INSERT INTO `h_menu`
VALUES (1, '用户列表', 0, '', 1, NULL, NULL, NULL, 1, 'sys:user:list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_menu`
VALUES (2, '角色列表', 0, NULL, 2, NULL, NULL, NULL, 1, 'sys:role:list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_menu`
VALUES (3, '新增用户', 1, NULL, 1, NULL, NULL, NULL, 1, 'sys:user:add', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_menu`
VALUES (4, '新增角色', 2, NULL, 3, NULL, NULL, NULL, 1, 'sys:role:add', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `h_menu`
VALUES (5, '修改用户', 2, NULL, 1, NULL, NULL, NULL, 1, 'sys:role:update', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for h_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `h_oper_log`;
CREATE TABLE `h_oper_log`
(
    `operId`        int NOT NULL,
    `title`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `businessType`  int NULL DEFAULT NULL,
    `method`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `requestMethod` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `operName`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `deptName`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `operUrl`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `operIp`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `operLocation`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `operParam`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `jsonResult`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `delFlag`       int NULL DEFAULT 0,
    `errorMsg`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `operTime`      datetime NULL DEFAULT NULL,
    PRIMARY KEY (`operId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for h_post
-- ----------------------------
DROP TABLE IF EXISTS `h_post`;
CREATE TABLE `h_post`
(
    `postId`     int NOT NULL,
    `postCode`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `postName`   char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `orderNum`   int NULL DEFAULT NULL,
    `delFlag`    int NULL DEFAULT 0,
    `createBy`   int NULL DEFAULT NULL,
    `createTime` datetime NULL DEFAULT NULL,
    `updateBy`   int NULL DEFAULT NULL,
    `updateTime` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`postId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_post
-- ----------------------------
INSERT INTO `h_post`
VALUES (2, 'hr', '人力资源', 1, 1, 3, '2022-09-13 15:31:00', NULL, NULL);

-- ----------------------------
-- Table structure for h_role
-- ----------------------------
DROP TABLE IF EXISTS `h_role`;
CREATE TABLE `h_role`
(
    `roleId`     int NOT NULL,
    `roleName`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `orderNum`   int NULL DEFAULT NULL,
    `delFlag`    int NULL DEFAULT 0,
    `createBy`   int NULL DEFAULT NULL,
    `createTime` datetime NULL DEFAULT NULL,
    `updateBy`   int NULL DEFAULT NULL,
    `updateTime` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_role
-- ----------------------------
INSERT INTO `h_role`
VALUES (1, 'super', 1, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for h_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `h_role_menu`;
CREATE TABLE `h_role_menu`
(
    `roleId` int NOT NULL,
    `menuId` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_role_menu
-- ----------------------------
INSERT INTO `h_role_menu`
VALUES (1, 2);

-- ----------------------------
-- Table structure for h_user
-- ----------------------------
DROP TABLE IF EXISTS `h_user`;
CREATE TABLE `h_user`
(
    `userId`     int NOT NULL,
    `userName`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `password`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delFlag`    int NULL DEFAULT 0,
    `createBy`   int NULL DEFAULT NULL,
    `createTime` datetime NULL DEFAULT NULL,
    `updateBy`   int NULL DEFAULT NULL,
    `updateTime` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_user
-- ----------------------------
INSERT INTO `h_user`
VALUES (3, 'zhangsan', '$2a$10$oVMNNsg3uFKtwpA5xAjwSe/3ivp/IQncBydyKrNM6i4RGz.Ia3wLq', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for h_user_post
-- ----------------------------
DROP TABLE IF EXISTS `h_user_post`;
CREATE TABLE `h_user_post`
(
    `userId` int NOT NULL,
    `postId` int NOT NULL,
    PRIMARY KEY (`userId`, `postId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for h_user_role
-- ----------------------------
DROP TABLE IF EXISTS `h_user_role`;
CREATE TABLE `h_user_role`
(
    `userId` int NOT NULL,
    `roleId` int NOT NULL,
    PRIMARY KEY (`userId`, `roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of h_user_role
-- ----------------------------
INSERT INTO `h_user_role`
VALUES (3, 1);

SET
FOREIGN_KEY_CHECKS = 1;

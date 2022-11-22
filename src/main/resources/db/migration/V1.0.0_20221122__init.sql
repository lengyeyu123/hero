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

 Date: 22/11/2022 20:17:55
*/

SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `type`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `createBy`   int                                                           NULL     DEFAULT NULL,
    `updateBy`   int                                                           NULL     DEFAULT NULL,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `id`           bigint                                                        NOT NULL AUTO_INCREMENT,
    `orderNum`     int                                                           NULL     DEFAULT NULL,
    `label`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `val`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `type`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `defaultState` int                                                           NULL     DEFAULT NULL,
    `createBy`     int                                                           NULL     DEFAULT NULL,
    `updateBy`     int                                                           NULL     DEFAULT NULL,
    `createTime`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime`   datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `delFlag`      tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `userName`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `realName`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `password`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `createBy`   int                                                           NULL     DEFAULT NULL,
    `updateBy`   int                                                           NULL     DEFAULT NULL,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'super2022', NULL, '$2a$10$S8.1QWneEVc8OY3uowqGWuqPeK0WDZAbsHoyrpwDTMa78TDxdpkrC', NULL, NULL,
        '2022-11-22 20:11:32', NULL, 'super man set sys config', 0);


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `orderNum`   int                                                           NULL     DEFAULT NULL,
    `createBy`   int                                                           NULL     DEFAULT NULL,
    `updateBy`   int                                                           NULL     DEFAULT NULL,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, 'admin', '管理员', NULL, NULL, NULL, '2022-11-22 20:11:32', NULL, NULL, 0);


-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`     bigint NOT NULL AUTO_INCREMENT,
    `userId` int    NOT NULL,
    `roleId` int    NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `parentId`   int                                                           NULL     DEFAULT NULL,
    `parentName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    `orderNum`   int                                                           NULL     DEFAULT NULL,
    `path`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    `component`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    `type`       tinyint                                                       NOT NULL,
    `perms`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    `icon`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    `createBy`   int                                                           NULL     DEFAULT NULL,
    `updateBy`   int                                                           NULL     DEFAULT NULL,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, '用户管理', NULL, NULL, NULL, NULL, 'component', 0, NULL, NULL, NULL, NULL, '2022-11-22 20:11:32', NULL,
        NULL, 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`     bigint NOT NULL AUTO_INCREMENT,
    `roleId` int    NOT NULL,
    `menuId` int    NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------


-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `id`            bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL     DEFAULT '' COMMENT '模块标题',
    `businessType`  int                                                            NULL     DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '方法名称',
    `requestMethod` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL     DEFAULT '' COMMENT '请求方式',
    `operName`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL     DEFAULT '' COMMENT '操作人员',
    `operUrl`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '请求URL',
    `operIp`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '主机地址',
    `operLocation`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '操作地点',
    `operParam`     varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '请求参数',
    `jsonResult`    varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '返回参数',
    `status`        int                                                            NULL     DEFAULT 0 COMMENT '操作状态（1正常 0异常）',
    `errorMsg`      varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '错误消息',
    `operTime`      datetime                                                       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_semester
-- ----------------------------
DROP TABLE IF EXISTS `t_semester`;
CREATE TABLE `t_semester`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `startDate`  date                                                          NOT NULL,
    `endDate`    date                                                          NOT NULL,
    `openState`  tinyint                                                       NOT NULL DEFAULT 1,
    `createBy`   int                                                           NULL     DEFAULT NULL,
    `updateBy`   int                                                           NULL     DEFAULT NULL,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_semester
-- ----------------------------
INSERT INTO `t_semester`
VALUES (1, '2022-09-01', '2023-01-31', 1, NULL, NULL, '2022-11-22 20:11:33', NULL, NULL, 0);

SET
    FOREIGN_KEY_CHECKS = 1;

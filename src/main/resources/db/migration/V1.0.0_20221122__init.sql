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
    `createBy`   bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`   bigint                                                        NOT NULL DEFAULT -1,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
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
    `orderNum`     int                                                           NOT NULL DEFAULT -1,
    `label`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `val`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `type`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `defaultState` int                                                           NOT NULL DEFAULT -1,
    `createBy`     bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`     bigint                                                        NOT NULL DEFAULT -1,
    `createTime`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`      tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
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
    `realName`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `password`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `createBy`   bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`   bigint                                                        NOT NULL DEFAULT -1,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` (`id`, `userName`, `password`)
VALUES (1, 'super2022', '$2a$10$S8.1QWneEVc8OY3uowqGWuqPeK0WDZAbsHoyrpwDTMa78TDxdpkrC');

INSERT INTO `sys_user` (`id`, `userName`, `password`)
VALUES (2, 'test', '$2a$10$S8.1QWneEVc8OY3uowqGWuqPeK0WDZAbsHoyrpwDTMa78TDxdpkrC');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `orderNum`   int                                                           NOT NULL DEFAULT -1,
    `createBy`   bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`   bigint                                                        NOT NULL DEFAULT -1,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` (`id`, `code`, `name`)
VALUES (1, 'admin', '院系管理员');
INSERT INTO `sys_role` (`id`, `code`, `name`)
VALUES (2, 'subjectAdmin', '课程负责人');
INSERT INTO `sys_role` (`id`, `code`, `name`)
VALUES (3, 'teacher', '阅卷教师');


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
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
values (1, 2, 2);
INSERT INTO `sys_user_role`
values (2, 2, 3);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `parentId`   int                                                           NOT NULL DEFAULT -1,
    `parentName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    `orderNum`   int                                                           NOT NULL DEFAULT -1,
    `path`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    `component`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    `type`       tinyint                                                       NOT NULL,
    `perms`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    `icon`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    `createBy`   bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`   bigint                                                        NOT NULL DEFAULT -1,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` (`id`, `code`, `name`, `path`, `component`, `type`, `icon`, `orderNum`)
VALUES (1, 'dashboard', '仪表盘', '/dashboard', 'basic', 0, 'mdi:monitor-dashboard', 1);
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `path`, `component`, `type`, `icon`, `orderNum`)
VALUES (2, 1, '仪表盘', 'dashboard_analysis', '分析页', '/dashboard/analysis', 'self', 1,
        'icon-park-outline:analysis', 1);
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `path`, `component`, `type`, `icon`, `orderNum`)
VALUES (3, 1, '仪表盘', 'dashboard_workbench', '工作台', '/dashboard/workbench', 'self', 1,
        'icon-park-outline:workbench', 2);

INSERT INTO `sys_menu` (`id`, `code`, `name`, `path`, `component`, `type`, `orderNum`)
VALUES (4, 'baseInfo', '基础信息', '/baseInfo', 'basic', 0, 2);

INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `path`, `component`, `type`, `orderNum`,
                        `perms`, `icon`)
VALUES (5, 4, '基础信息', 'baseInfo_college', '院系管理', '/baseInfo/college', 'self', 1, 1, 'baseInfo:college:list',
        'icon-park-outline:workbench');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (6, 4, '基础信息', 'baseInfo_collage-add', '院系新增', 2, 'baseInfo:college:add');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (7, 4, '基础信息', 'baseInfo_collage-update', '院系修改', 2, 'baseInfo:college:update');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (8, 4, '基础信息', 'baseInfo_collage-del', '院系删除', 2, 'baseInfo:college:del');


INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `path`, `component`, `type`, `orderNum`,
                        `perms`, `icon`)
VALUES (9, 4, '基础信息', 'baseInfo_major', '专业管理', '/baseInfo/major', 'self', 1, 2, 'baseInfo:major:list',
        'icon-park-outline:workbench');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (10, 4, '基础信息', 'baseInfo_major-add', '专业新增', 2, 'baseInfo:major:add');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (11, 4, '基础信息', 'baseInfo_major-update', '专业修改', 2, 'baseInfo:major:update');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (12, 4, '基础信息', 'baseInfo_major-del', '专业删除', 2, 'baseInfo:major:del');


INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `path`, `component`, `type`, `orderNum`,
                        `perms`, `icon`)
VALUES (13, 4, '基础信息', 'baseInfo_teacher', '教师管理', '/baseInfo/teacher', 'self', 1, 3, 'baseInfo:teacher:list',
        'icon-park-outline:workbench');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (14, 4, '基础信息', 'baseInfo_teacher-add', '教书新增', 2, 'baseInfo:teacher:add');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (15, 4, '基础信息', 'baseInfo_teacher-update', '教师修改', 2, 'baseInfo:teacher:update');
INSERT INTO `sys_menu` (`id`, `parentId`, `parentName`, `code`, `name`, `type`, `perms`)
values (16, 4, '基础信息', 'baseInfo_teacher-del', '教师删除', 2, 'baseInfo:teacher:del');


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
  AUTO_INCREMENT = 100
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
insert into `sys_role_menu`
values (1, 1, 1);
insert into `sys_role_menu`
values (2, 1, 2);
insert into `sys_role_menu`
values (3, 1, 3);
insert into `sys_role_menu`
values (4, 2, 2);
insert into `sys_role_menu`
values (5, 2, 3);
insert into `sys_role_menu`
values (6, 2, 4);
insert into `sys_role_menu`
values (7, 2, 5);
insert into `sys_role_menu`
values (8, 2, 6);
insert into `sys_role_menu`
values (9, 2, 7);
insert into `sys_role_menu`
values (10, 2, 3);
insert into `sys_role_menu`
values (11, 2, 2);
insert into `sys_role_menu`
values (12, 2, 1);

insert into `sys_role_menu`
values (13, 3, 3);

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
    `createBy`   bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`   bigint                                                        NOT NULL DEFAULT -1,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
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
INSERT INTO `t_semester` (`id`, `startDate`, `endDate`)
VALUES (1, '2022-09-01', '2023-01-31');


-- ----------------------------
-- Table structure for t_college
-- ----------------------------
DROP TABLE IF EXISTS `t_college`;
CREATE TABLE `t_college`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `adminUserId` bigint                                                        NOT NULL COMMENT '院系管理员用户id',
    `createBy`    bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`    bigint                                                        NOT NULL DEFAULT -1,
    `createTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`     tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_college
-- ----------------------------

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `collegeId`  bigint                                                        NOT NULL COMMENT '所属院系',
    `createBy`   bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`   bigint                                                        NOT NULL DEFAULT -1,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_major
-- ----------------------------

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `code`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `majorId`    bigint                                                        NOT NULL COMMENT '所属专业',
    `semesterId` bigint                                                        NOT NULL COMMENT '所属学年学期',
    `createBy`   bigint                                                        NOT NULL DEFAULT -1,
    `updateBy`   bigint                                                        NOT NULL DEFAULT -1,
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `remark`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    `delFlag`    tinyint                                                       NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_subject
-- ----------------------------

SET
    FOREIGN_KEY_CHECKS = 1;

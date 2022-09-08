/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : SQL Server
 Source Server Version : 15002095 (15.00.2095)
 Source Host           : localhost:1433
 Source Catalog        : hero
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002095 (15.00.2095)
 File Encoding         : 65001

 Date: 07/09/2022 15:47:56
*/


-- ----------------------------
-- Table structure for h_dict_data
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_dict_data]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_dict_data]
GO

CREATE TABLE [dbo].[h_dict_data]
(
    [dictCode]     int IDENTITY (1,1)                      NOT NULL,
    [orderNum]     int                                     NULL,
    [dictLabel]    nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [dictValue]    nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [dictType]     nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [defaultState] int DEFAULT 0                           NULL,
    [state]        int DEFAULT 1                           NULL,
    [createBy]     int                                     NULL,
    [createTime]   datetime2(7)                            NULL,
    [updateBy]     int                                     NULL,
    [updateTime]   datetime2(7)                            NULL,
    [remark]       nchar(10) COLLATE Chinese_PRC_CI_AS     NULL
)
GO

ALTER TABLE [dbo].[h_dict_data]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_dict_data
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_dict_data] ON
GO

SET IDENTITY_INSERT [dbo].[h_dict_data] OFF
GO


-- ----------------------------
-- Table structure for h_dict_type
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_dict_type]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_dict_type]
GO

CREATE TABLE [dbo].[h_dict_type]
(
    [dictId]     int IDENTITY (1,1)                      NOT NULL,
    [dictName]   nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [dictType]   nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [state]      int DEFAULT 1                           NULL,
    [createBy]   int                                     NULL,
    [createTime] datetime2(7)                            NULL,
    [updateBy]   int                                     NULL,
    [updateTime] datetime2(7)                            NULL,
    [remark]     nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[h_dict_type]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_dict_type
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_dict_type] ON
GO

SET IDENTITY_INSERT [dbo].[h_dict_type] OFF
GO


-- ----------------------------
-- Table structure for h_menu
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_menu]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_menu]
GO

CREATE TABLE [dbo].[h_menu]
(
    [menuId]     int IDENTITY (1,1)                     NOT NULL,
    [menuName]   nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [parentId]   int                                    NULL,
    [parentName] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [orderNum]   int                                    NULL,
    [path]       nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [component]  nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [menuType]   nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [state]      int DEFAULT 1                          NULL,
    [perms]      nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [icon]       nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [createBy]   int                                    NULL,
    [createTime] datetime2(7)                           NULL,
    [updateBy]   int                                    NULL,
    [updateTime] datetime2(7)                           NULL
)
GO

ALTER TABLE [dbo].[h_menu]
    SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'GET:/post/list 请求路径',
     'SCHEMA', N'dbo',
     'TABLE', N'h_menu',
     'COLUMN', N'path'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'路由组件',
     'SCHEMA', N'dbo',
     'TABLE', N'h_menu',
     'COLUMN', N'component'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'菜单类型 (M目录 C菜单 F按钮)',
     'SCHEMA', N'dbo',
     'TABLE', N'h_menu',
     'COLUMN', N'menuType'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'权限字符 *:*:*拥有所有权限 super',
     'SCHEMA', N'dbo',
     'TABLE', N'h_menu',
     'COLUMN', N'perms'
GO


-- ----------------------------
-- Records of h_menu
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_menu] ON
GO

INSERT INTO [dbo].[h_menu] ([menuId], [menuName], [parentId], [parentName], [orderNum], [path], [component], [menuType],
                            [state], [perms], [icon], [createBy], [createTime], [updateBy], [updateTime])
VALUES (N'1', N'用户列表', N'0', N'', N'1', NULL, NULL, NULL, N'1', N'sys:user:list', NULL, NULL, NULL, NULL, NULL)
GO

INSERT INTO [dbo].[h_menu] ([menuId], [menuName], [parentId], [parentName], [orderNum], [path], [component], [menuType],
                            [state], [perms], [icon], [createBy], [createTime], [updateBy], [updateTime])
VALUES (N'2', N'角色列表', N'0', NULL, N'2', NULL, NULL, NULL, N'1', N'sys:role:list', NULL, NULL, NULL, NULL, NULL)
GO

SET IDENTITY_INSERT [dbo].[h_menu] OFF
GO


-- ----------------------------
-- Table structure for h_oper_log
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_oper_log]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_oper_log]
GO

CREATE TABLE [dbo].[h_oper_log]
(
    [operId]        int IDENTITY (1,1)                       NOT NULL,
    [title]         nvarchar(50) COLLATE Chinese_PRC_CI_AS   NULL,
    [businessType]  int                                      NULL,
    [method]        nvarchar(100) COLLATE Chinese_PRC_CI_AS  NULL,
    [requestMethod] nvarchar(10) COLLATE Chinese_PRC_CI_AS   NULL,
    [operName]      nvarchar(50) COLLATE Chinese_PRC_CI_AS   NULL,
    [deptName]      nvarchar(50) COLLATE Chinese_PRC_CI_AS   NULL,
    [operUrl]       nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
    [operIp]        nvarchar(128) COLLATE Chinese_PRC_CI_AS  NULL,
    [operLocation]  nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
    [operParam]     nvarchar(2000) COLLATE Chinese_PRC_CI_AS NULL,
    [jsonResult]    nvarchar(2000) COLLATE Chinese_PRC_CI_AS NULL,
    [state]         int DEFAULT 1                            NULL,
    [errorMsg]      nvarchar(2000) COLLATE Chinese_PRC_CI_AS NULL,
    [operTime]      datetime2(7)                             NULL
)
GO

ALTER TABLE [dbo].[h_oper_log]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_oper_log
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_oper_log] ON
GO

INSERT INTO [dbo].[h_oper_log] ([operId], [title], [businessType], [method], [requestMethod], [operName], [deptName],
                                [operUrl], [operIp], [operLocation], [operParam], [jsonResult], [state], [errorMsg],
                                [operTime])
VALUES (N'1', N'认证', N'0', N'com.han.hero.project.controller.AuthController.getUserInfo()', NULL, N'zhangsan', NULL,
        N'/auth/getUserInfo', N'0:0:0:0:0:0:0:1', NULL, N'{}',
        N'{"code":2000,"message":"成功","defaultMsg":null,"data":{"userId":3,"userName":"zhangsan","password":"$2a$10$oVMNNsg3uFKtwpA5xAjwSe/3ivp/IQncBydyKrNM6i4RGz.Ia3wLq","state":1}}',
        N'1', NULL, N'2022-09-05 17:41:34.5100000')
GO

INSERT INTO [dbo].[h_oper_log] ([operId], [title], [businessType], [method], [requestMethod], [operName], [deptName],
                                [operUrl], [operIp], [operLocation], [operParam], [jsonResult], [state], [errorMsg],
                                [operTime])
VALUES (N'2', N'认证', N'0', N'com.han.hero.project.controller.AuthController.getUserInfo()', N'GET', N'zhangsan', NULL,
        N'/auth/getUserInfo', N'0:0:0:0:0:0:0:1', NULL, N'{}',
        N'{"code":2000,"message":"成功","defaultMsg":null,"data":{"userId":3,"userName":"zhangsan","password":"$2a$10$oVMNNsg3uFKtwpA5xAjwSe/3ivp/IQncBydyKrNM6i4RGz.Ia3wLq","state":1}}',
        N'1', NULL, N'2022-09-05 18:00:33.1266667')
GO

INSERT INTO [dbo].[h_oper_log] ([operId], [title], [businessType], [method], [requestMethod], [operName], [deptName],
                                [operUrl], [operIp], [operLocation], [operParam], [jsonResult], [state], [errorMsg],
                                [operTime])
VALUES (N'1002', N'认证', N'0', N'com.han.hero.project.controller.AuthController.getUserInfo()', N'GET', N'zhangsan',
        NULL, N'/auth/getUserInfo', N'0:0:0:0:0:0:0:1', NULL, N'{}',
        N'{"code":2000,"message":"成功","defaultMsg":null,"data":{"userId":3,"userName":"zhangsan","password":"$2a$10$oVMNNsg3uFKtwpA5xAjwSe/3ivp/IQncBydyKrNM6i4RGz.Ia3wLq","state":1}}',
        N'1', NULL, N'2022-09-06 16:51:04.3200000')
GO

SET IDENTITY_INSERT [dbo].[h_oper_log] OFF
GO


-- ----------------------------
-- Table structure for h_post
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_post]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_post]
GO

CREATE TABLE [dbo].[h_post]
(
    [postId]     int IDENTITY (1,1)                     NOT NULL,
    [postCode]   nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [postName]   nchar(10) COLLATE Chinese_PRC_CI_AS    NULL,
    [orderNum]   int                                    NULL,
    [state]      int DEFAULT 1                          NULL,
    [createBy]   int                                    NULL,
    [createTime] datetime2(7)                           NULL,
    [updateBy]   int                                    NULL,
    [updateTime] datetime2(7)                           NULL
)
GO

ALTER TABLE [dbo].[h_post]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_post
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_post] ON
GO

SET IDENTITY_INSERT [dbo].[h_post] OFF
GO


-- ----------------------------
-- Table structure for h_role
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_role]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_role]
GO

CREATE TABLE [dbo].[h_role]
(
    [roleId]     int IDENTITY (1,1)                     NOT NULL,
    [roleName]   nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [orderNum]   int                                    NULL,
    [state]      int DEFAULT 1                          NULL,
    [createBy]   int                                    NULL,
    [createTime] datetime2(7)                           NULL,
    [updateBy]   int                                    NULL,
    [updateTime] datetime2(7)                           NULL
)
GO

ALTER TABLE [dbo].[h_role]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_role
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_role] ON
GO

INSERT INTO [dbo].[h_role] ([roleId], [roleName], [orderNum], [state], [createBy], [createTime], [updateBy],
                            [updateTime])
VALUES (N'1', N'super', N'1', N'1', NULL, NULL, NULL, NULL)
GO

SET IDENTITY_INSERT [dbo].[h_role] OFF
GO


-- ----------------------------
-- Table structure for h_role_menu
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_role_menu]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_role_menu]
GO

CREATE TABLE [dbo].[h_role_menu]
(
    [roleId] int NOT NULL,
    [menuId] int NOT NULL
)
GO

ALTER TABLE [dbo].[h_role_menu]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_role_menu
-- ----------------------------
INSERT INTO [dbo].[h_role_menu] ([roleId], [menuId])
VALUES (N'1', N'2')
GO


-- ----------------------------
-- Table structure for h_user
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_user]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_user]
GO

CREATE TABLE [dbo].[h_user]
(
    [userId]     int IDENTITY (1,1)                      NOT NULL,
    [userName]   nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
    [password]   nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [state]      int DEFAULT 1                           NULL,
    [createBy]   int                                     NULL,
    [createTime] datetime2(7)                            NULL,
    [updateBy]   int                                     NULL,
    [updateTime] datetime2(7)                            NULL
)
GO

ALTER TABLE [dbo].[h_user]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_user
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_user] ON
GO

INSERT INTO [dbo].[h_user] ([userId], [userName], [password], [state], [createBy], [createTime], [updateBy],
                            [updateTime])
VALUES (N'3', N'zhangsan', N'$2a$10$oVMNNsg3uFKtwpA5xAjwSe/3ivp/IQncBydyKrNM6i4RGz.Ia3wLq', N'1', NULL, NULL, NULL,
        NULL)
GO

SET IDENTITY_INSERT [dbo].[h_user] OFF
GO


-- ----------------------------
-- Table structure for h_user_post
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_user_post]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_user_post]
GO

CREATE TABLE [dbo].[h_user_post]
(
    [userId] int NOT NULL,
    [postId] int NOT NULL
)
GO

ALTER TABLE [dbo].[h_user_post]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for h_user_role
-- ----------------------------
IF EXISTS(SELECT *
          FROM sys.all_objects
          WHERE object_id = OBJECT_ID(N'[dbo].[h_user_role]')
            AND type IN ('U'))
    DROP TABLE [dbo].[h_user_role]
GO

CREATE TABLE [dbo].[h_user_role]
(
    [userId] int NOT NULL,
    [roleId] int NOT NULL
)
GO

ALTER TABLE [dbo].[h_user_role]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_user_role
-- ----------------------------
INSERT INTO [dbo].[h_user_role] ([userId], [roleId])
VALUES (N'3', N'1')
GO


-- ----------------------------
-- Auto increment value for h_dict_data
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[h_dict_data]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table h_dict_data
-- ----------------------------
ALTER TABLE [dbo].[h_dict_data]
    ADD CONSTRAINT [PK_h_dict_data] PRIMARY KEY CLUSTERED ([dictCode])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for h_dict_type
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[h_dict_type]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table h_dict_type
-- ----------------------------
ALTER TABLE [dbo].[h_dict_type]
    ADD CONSTRAINT [PK_h_dict_type] PRIMARY KEY CLUSTERED ([dictId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for h_menu
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[h_menu]', RESEED, 2)
GO


-- ----------------------------
-- Primary Key structure for table h_menu
-- ----------------------------
ALTER TABLE [dbo].[h_menu]
    ADD CONSTRAINT [PK_h_menu] PRIMARY KEY CLUSTERED ([menuId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for h_oper_log
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[h_oper_log]', RESEED, 1002)
GO


-- ----------------------------
-- Primary Key structure for table h_oper_log
-- ----------------------------
ALTER TABLE [dbo].[h_oper_log]
    ADD CONSTRAINT [PK_h_oper_log] PRIMARY KEY CLUSTERED ([operId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for h_post
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[h_post]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table h_post
-- ----------------------------
ALTER TABLE [dbo].[h_post]
    ADD CONSTRAINT [PK_h_post] PRIMARY KEY CLUSTERED ([postId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for h_role
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[h_role]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table h_role
-- ----------------------------
ALTER TABLE [dbo].[h_role]
    ADD CONSTRAINT [PK_h_role] PRIMARY KEY CLUSTERED ([roleId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Uniques structure for table h_role_menu
-- ----------------------------
ALTER TABLE [dbo].[h_role_menu]
    ADD CONSTRAINT [UQ__h_role_m__9E2C413C8D8B1376] UNIQUE NONCLUSTERED ([roleId] ASC, [menuId] ASC)
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for h_user
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[h_user]', RESEED, 3)
GO


-- ----------------------------
-- Uniques structure for table h_user
-- ----------------------------
ALTER TABLE [dbo].[h_user]
    ADD CONSTRAINT [UQ__h_user__66DCF95C8C2A6C5B] UNIQUE NONCLUSTERED ([userName] ASC)
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table h_user
-- ----------------------------
ALTER TABLE [dbo].[h_user]
    ADD CONSTRAINT [PK_h_user] PRIMARY KEY CLUSTERED ([userId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table h_user_post
-- ----------------------------
ALTER TABLE [dbo].[h_user_post]
    ADD CONSTRAINT [PK_h_user_post] PRIMARY KEY CLUSTERED ([userId], [postId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Uniques structure for table h_user_role
-- ----------------------------
ALTER TABLE [dbo].[h_user_role]
    ADD CONSTRAINT [UQ__h_user_r__7743989CF6133B8D] UNIQUE NONCLUSTERED ([userId] ASC, [roleId] ASC)
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table h_user_role
-- ----------------------------
ALTER TABLE [dbo].[h_user_role]
    ADD CONSTRAINT [PK_h_user_role] PRIMARY KEY CLUSTERED ([userId], [roleId])
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
        ON [PRIMARY]
GO


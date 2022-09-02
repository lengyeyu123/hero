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

 Date: 02/09/2022 17:07:27
*/


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
    [icon]       nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL
)
GO

ALTER TABLE [dbo].[h_menu]
    SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of h_menu
-- ----------------------------
SET IDENTITY_INSERT [dbo].[h_menu] ON
GO

INSERT INTO [dbo].[h_menu] ([menuId], [menuName], [parentId], [parentName], [orderNum], [path], [component], [menuType],
                            [state], [perms], [icon])
VALUES (N'1', N'用户列表', N'0', N'', N'1', NULL, NULL, NULL, N'1', N'sys:user:list', NULL)
GO

INSERT INTO [dbo].[h_menu] ([menuId], [menuName], [parentId], [parentName], [orderNum], [path], [component], [menuType],
                            [state], [perms], [icon])
VALUES (N'2', N'角色列表', N'0', NULL, N'2', NULL, NULL, NULL, N'1', N'sys:role:list', NULL)
GO

SET IDENTITY_INSERT [dbo].[h_menu] OFF
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
    [postId]   int IDENTITY (1,1)                     NOT NULL,
    [postCode] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [postName] nchar(10) COLLATE Chinese_PRC_CI_AS    NULL,
    [orderNum] int                                    NULL,
    [state]    int DEFAULT 1                          NULL
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
    [roleId]   int IDENTITY (1,1)                     NOT NULL,
    [roleName] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [orderNum] int                                    NULL,
    [state]    int DEFAULT 1                          NULL
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

INSERT INTO [dbo].[h_role] ([roleId], [roleName], [orderNum], [state])
VALUES (N'1', N'super', N'1', N'1')
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
    [userId]   int IDENTITY (1,1)                      NOT NULL,
    [userName] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
    [password] nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [state]    int DEFAULT 1                           NULL
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

INSERT INTO [dbo].[h_user] ([userId], [userName], [password], [state])
VALUES (N'3', N'zhangsan', N'$2a$10$oVMNNsg3uFKtwpA5xAjwSe/3ivp/IQncBydyKrNM6i4RGz.Ia3wLq', N'1')
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


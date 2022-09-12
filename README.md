# hero

spring boot spring security + jwt rbac 后台接口

## TODO 🎉🎉

1. 集成Spring Security🎉🎉
2. flyway运行前，检查是否创建数据库，若未创建数据库则创建数据库
3. 多数据源 flyway 支持 多数据源切换
4. 管理员操作 记录日志 🎉🎉
5. redis 集成🎉🎉
6. redis 工具类封装 🎉🎉
7. 用户登录jwt存储到redis -> 在线踢人功能
8. 定时任务
9. 正确配置cors 解决跨域问题 🎉🎉

## 项目启动

1. 在SQL server中创建数据库 hero 并修改application.yml中的数据库连接池的用户名密码
2. 启动HeroApplication中的main方法 （flyway自动创建表，插入基础数据）

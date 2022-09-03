# hero

spring boot spring security + jwt rbac 后台接口

## TODO 🎉🎉

1. 集成Spring Security🎉🎉
2. flyway运行前，检查是否创建数据库，若未创建数据库则创建数据库
3. 多数据库 flyway 支持 多数据源切换
4. 管理员操作日志AOP切面记录
5. redis 集成
6. 用户登录jwt存储到redis -> 在线踢人功能
7. 定时任务
8. 其他

## 项目启动

1. 在SQL server中创建数据库 hero 并修改application.yml中的数据库连接池的用户名密码
2. 启动HeroApplication中的main方法 （flyway自动创建表，插入基础数据）

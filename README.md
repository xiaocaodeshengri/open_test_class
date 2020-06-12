## 项目结构说明：

​    

整体设计：前后端分离，便于以后服务扩展不做单体项目





### 后端项目

系统后端接口部分，使用SpringBoot+SpringCloud进行微服务架构，Gateway阿里巴巴的Nacos等组件搭建了项目的基础环境。

项目中还使用MyBatisPlus进行持久层的操作，使用了OAuth2+JWT实现了分布式的访问，项目中整合了SpringSecurity进行了权限控制。





后端使用技术：

| 名称         | 作用                                       |
| :----------- | ------------------------------------------ |
| NACOS        | 服务注册中心                               |
| REDIS        | 缓存，应用于用户权限信息缓存做到分布式缓存 |
| Mybatis-plus | 持久层框架                                 |



后端服务项目:

| 名称        | 作用                                                         |
| :---------- | ------------------------------------------------------------ |
| common      | 公共组件，包括常用工具类、security依赖包、异常处理以及返回值定义 |
| gateway     | 网关组件，应用后续将服务扩展                                 |
| service_acl | 用于目前业务管理，目前为用户管理、订阅管理、课程管理         |

   

### 前端项目：

  edu-online-web 

系统前端部分，使用主流的前端框架Vue，搭建页面环境使用了vue-admin-template模板，使用Element-ui进行页面布局。





数据库表设计：

| 名称                | 作用       |
| :------------------ | ---------- |
| acl_permission      | 菜单表     |
| acl_role            | 角色表     |
| acl_role_permission | 角色菜单表 |
| acl_user            | 用户表     |
| acl_user_role       | 用户角色表 |
| edu_sub             | 订阅表     |
| edu_course          | 课程表     |
| edu_chapter         | 课程章节表 |

   








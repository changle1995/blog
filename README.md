# Java多人博客系统

### 涉及技术：

* JDK8
* MySQL
* Spring-boot
* Spring-data-jpa
* Spring-security
* Swagger2

### 启动：
 - main方法运行
 ```
 配置：src/main/resources/dev(prod)_conf/application-datasource.yml (数据库账号密码)、运行sql/blog.sql文件向数据库导入初始数据
 运行：src/main/java/com/example/blog/BootApplication.java
 访问：http://localhost:10080/
 账号：默认管理员账号为 admin/12345
```

### 最新版本(1.0)：
* 权限功能
    ###### 分为用户、角色、权限、路由
    ```
    用户可拥有多个角色
    角色可拥有多个权限,权限对应一个url路径
    角色可拥有多个路由,路由为配合前端项目使用,可控制按钮权限
    ```
* 博客功能
    ###### 分为板块、文章、标签、评论
    ```
    板块下有多个文章
    文章可有多个评论
    文章可有多个标签
    ```

### 未来版本预计(x.x)：
* 增加QQ，微信，Github等登录方式
* 增加测试功能
* 增加缓存cache
* 性能优化
* 分拆为模块，使用spring cloud分布式
* 增加具体的异常处理
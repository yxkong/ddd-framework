说明

### ddd-parent

- 定义了框架的pom
- ddd-common ddd应用框架公共包
- ddd-code-generator ddd应用框架代码生成器  
- eureka-server

### ddd-demo

- 一个可以跑起来的demo
- 依据此demo生成了ddd应用框架脚手架ddd-archetype

### ddd-archetype

- ddd应用框架脚手架

### 项目初始化

#### 依赖环境

- docker
- redis
- kafka
- mysql（demo和demox两个库）
- eureka

#### docker构建依赖环境

##### 构建eureka

在ddd-parent 项目里的eureka-server

打开终端，切换到该目录下，执行以下命令

```shell
# 1 构建jar包
mvn clean install
# 2 构建 docker镜像
docker build -t 5ycode/eureka:0.1 .

```

#### 启动依赖服务

进入ddd-demo/doc 目录中

修改docker-compose.yml中的todo（可以相对路径）

```shell
# 启动容器
sudo docker-compose -f docker-compose.yml up -d

会创建两个库， demo和demox
- demo库放的是主业务渠道的数据。
- demox库放的是其他业务渠道的数据。
```

启动以后初始化脚本都已经执行完毕

#### 启动应用

```
DemoStarter
```



#### 发送短信

```
# 发送一条短信（虚拟的在demo.t_sms_log表中）tenantId=1001进入demo库中
curl --location --request POST 'http://127.0.0.1:8001/demo/sms/sendBySlidingBlock' \
--header 'token: token' \
--header 'proId: self-test' \
--header 'Content-Type: application/json' \
--data-raw '{
    "mobile": "15200000000",
    "slidingBlockId": "xxxx",
    "slidingBlockSupplier": "shumei",
    "smsType": 1,
    "tenantId": 1001,
    "proId": "postman",
    "env": "postman"
}'

# 拿着验证码注册
curl --location --request POST 'http://127.0.0.1:8001/demo/account/registerWithoutPwd' \
--header 'token: token' \
--header 'proId: self-test' \
--header 'Content-Type: application/json' \
--data-raw '{
    "mobile":"15200000000",
    "verifyCode":"047351",
    "tenantId":1001,
    "proId":"postman",
    "env":"postman"
}'
会返回一个token 

# 拿着注册返回来的token可以干别的
curl --location --request POST 'http://127.0.0.1:8001/demo/account/accountLog' \
--header 'token: e9706f5facf140e1b4f57482ec7fa9f3' \
--header 'proId: self-test' \
```






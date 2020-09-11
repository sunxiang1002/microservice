# SpringCloud2020 (代码完结，脑图见下方链接)
## 脑图百度网盘地址：https://pan.baidu.com/s/1IRUuc3dCXp0GKEjP72u12Q
提取码：iw8t

#### 介绍
尚硅谷周阳SpringCloud第二季教程，边学边敲！
[视频地址](https://www.bilibili.com/video/av93813318)

#### 软件架构
- SpringCloud：Hoxton.SR1
- SpringBoot：2.2.2RELEASE
- SpringCloud Alibaba：2.1.0.RELEASE
- JAVA：8
- Maven：3.5及以上
- MySQL：5.7及以上


#### P8~P10：支付模块构建与测试-cloud-provider-payment8001
1. 建Module
2. 改pom.xml
3. 写配置文件YML
4. 主启动类
5. 业务类
    - Controller -> Service -> ServiceImpl -> Dao (Mapper) -> Entity
#### P9~P14：消费订单模块与项目重构
 **重点：** 重复性Entity集中提取到cloud-api-commons中，其他子模块可以pom.xml中引入cloud-api-commons，即可获得Entity类
```
<!--引入自己定义的api调用包，可以使用Payment支付Entity-->
<dependency>
    <groupId>com.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```
#### P15~P26：Eureka的服务注册与发现
- Eureka Server：提供服务注册服务
- Eureka Client：通过注册中心进行访问

 **重点：** Discovery服务发现
```
@Resource
private DiscoveryClient discoveryClient;

@GetMapping(value = "/payment/discovery")
public Object discovery() {
    //查询Eureka上面注册了几个服务
    List<String> services =  discoveryClient.getServices();
    for (String service : services) {
        log.info("service:"+service);
    }

    //查询对应名字下面有几个示例
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
        log.info(instance.getServiceId()+"\t"+instance.getHost()
            +"\t"+instance.getPort()+"\t"+instance.getUri());
    }
    return this.discoveryClient;
}
```
同时需要在主程序服务启动类上添加 **@EnableDiscoveryClient注解！** 
#### P27~P30：使用zookeeper作为服务注册发现中间件

 **重点：** SpringCloud引入的starter-zookeeper-discovery中自带有zookeeper的jar包，但是与我们项目环境中的版本不匹配，需要先排除，再引入我们所需的版本
```
<!-- SpringBoot整合zookeeper客户端 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
    <!-- 先排除自带的zookeeper3.5.3 -->
    <exclusions>
        <exclusion>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- 添加zookeeper3.4.11版本 -->
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.4.11</version>
</dependency>
```


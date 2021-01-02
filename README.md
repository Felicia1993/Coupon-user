
# 优惠券项目
## 基于优惠券系统的需求分析
### 1.对外接口、协议：dubbo
## 2.接口内容
 - 优惠券列表接口 缓存更新机制（1.每个5分钟刷新最新的优惠券列表 2.定时更新）  
- 领取优惠券接口 生成券码、券码唯一，雪花算法  
-  获取用户当前拥有的优惠券 基于用户级别做缓存  

-  优惠券核销接口 接受订单系统发来的结算信息  

  
mysql设计遵守的公式  

1.字段长度 能使用int就不要用varchar，char能用varchar就不要使用varchar(256)  

2.字段选择 固定长度的类型最好使用char， 能使用tinyint就不要使用int  

3.默认值 最好给每个字段一个默认值，最好不为null，即not null default  

4.适当索引 为每个表创建默认索引
## 分布式系统RPC远程调用协议
### RPC是什么  

RPC是指远程过程调用，也就是两台服务器A、B，一个应用部署在A上，想要调用B服务器上应用提供的函数/方法, 需要通过网络来表达调用的寓意和传达调用的数据 

### RPC怎么做 
- 连接：通过在客户端和服务器之间建立TCP连接，远程过程调用的所有较好的数据都Z这个连接里传输 

- 寻址：A服务器上的应用怎么告诉底层的TPC框架，如何连接到B服务器（如主机IP地址）以及特定的端口 

- 编码：网络协议是基于二进制的，内存中的参数的值要序列化成二进制的形式，也就是序列化(Serialize)或编辑（marshai), 通过寻址和传输将序列化的二进制发送给B服务器 

- 处理返回值：返回值还要发哦是哪个会服务器A上的应用，也要经过序列化的方式发送，服务器A接到后，再反序列化，恢复为内存中的表达方式，交给A服务器上的应用 

RPC的协议有很多，比如最早的CORBA，java RMI，Web Service的RPC风格，Hessian，Thrift，甚至Rest API
### Dubbo是什么? 

Apache Dubbo是一个高性能，轻量级，基于java的RPC框架。Dubbo提供三个关键功能，包括接口的远程调用，容错和负载均衡以及自动服务注册和发现。  

Dubbo功能分析 

紫色 init 3、5async 4 sync
补充一个图 

## Zookeeper 
### 调用功能职责 

- 服务容器负责启动，加载，运行服务提供者 

- 服务提供者在启动时，向注册中心注册自己的服务 

- 服务消费者在启动时，向注册中心订阅自己所需的服务 

- 注册中心返回服务提供者地址列表给消费者，如果变更，注册中心将基于长连接推送变更数据给消费者 

服务消费者，从提供者列表中，基于软均衡算法，选一台提供者进行调用，如果调用失败，再选一台进行调用。
软负载均衡区别于负载均衡，是基于软件的负载均衡算法 

服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次数据到监控中心 

### map（调用id，times） map（调用id，int），防止与集装箱进行汇总再上报到注册中心 

### Zookeeper是什么

Zookeeper是一种集中式服务，用于维护配置信息，命名，提供分布式同步和提供组服务 

Zookeeper在Dubbo中的主要功能  

- 当提供者出现断电等停机异常时，注册中心能自动删除提供者信息 

- 当注册中心重启后，能自动恢复祖册数据，以及订阅请求 

- 当会话过期时，能自动恢复注册数据，以及订阅请求 

- 当设置<dubbo:registry check="false"/>时，记录失败注册和订阅请求，后台定时重试 
Zookeeper遵守CP原则，任何时候对Zookeeper的访问请求都能得到一致的数据结果，同时系统对网络分割具备容错性 

### Zookeeper搭建 

Zookeeper下载 

地址：http://www.apache.org/dyn/closer.cgi/zookeeper 

配置 zoo.cfg 

tickTime=2000 

dataDir=/usr/local/data 

dataLogDir=/usr/local/logs 

clientPort=2181
## Dubbo调用可视化界面
dubbo-admin的搭建
下载地址：git clone https://github.com/apache/dubbo-admin.git 

cd dubbo-admin-server/src/main/resources/application.properties修改配置地址 

启动命令 cd dubbo-admin-distribution/target 

nohup java -jar dubbo-admin-0.1.jar启动命令 

## 微服务单机实现集群部署
### 单机如何搭建集群
服务对外提供的是端口，度单口状态是监听状态（listen），每个服务器的对外端口可以非常多，通过反向代理进行端口映射  

### dubbo负载均衡策略 
轮询调度算法Round Robin Scheduling

- 轮询调度算法的原理是每一次把来自用户的请求轮流分配给内部中的服务器，从1开始，直到N，然后开始重新循环。算法的优点是其简洁性，它无需记录当前所有连接的状态，所以它是一种无状态调度。 

最少活跃调用 LeastActive LoadBalance 

- 相同活跃数的随机，活跃数指调用	前后计数差 

- 使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大 

## 基于JVM的shutdownHook优雅关闭 
### 服务启动端口冲突解决方案 Address already in use 
- 修改启动端口指定server.port 

- lsof -i:<port>/netstat -anp|grep <port> ==>找到启动端口的进程pid ==> kill -9执行进程强杀 

- kill -15配合shutdownHook实现优雅关闭 

JAVA进程优雅关闭的意义 

- 关闭socket连接 

- 清理临时文件 

- 发送消息通知给订阅方，告知自己下线 

- 各种资源的释放 jekins钩子，往git提交代码==>执行了自动化构建==>服务重启 dubbo spi提供了钩子 






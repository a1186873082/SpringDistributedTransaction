## SpringCloud+eureka+Seata Sample

**demo主要引入了Seata中AT,TCC这2种模式解决分布式事务的问题。记录下来以便查找**

##### 1. AT模式

​	**业务模块**：order，user

​	下载最新seata-server 1.3.0的压缩包，地址： https://github.com/seata/seata/releases 

​	或者用源码启动，下载地址：git clone -b 1.3.0 https://github.com/seata/seata.git

​	源码启动可以参考文末，此处只介绍Seata-server客户端启动

1. 将/seata/conf中file.conf的mode改为db，并修改db参数未自身的db连接，其中server的vgroupMapping参数必须保证与client端的vgroupMapping保持一致

   ```
   
   ## transaction log store, only used in seata-server
   store {
     ## store mode: file、db
     mode = "db"
   
     ## file store property
     file {
       ## store location dir
       dir = "sessionStore"
       # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
       maxBranchSessionSize = 16384
       # globe session size , if exceeded throws exceptions
       maxGlobalSessionSize = 512
       # file buffer size , if exceeded allocate new buffer
       fileWriteBufferCacheSize = 16384
       # when recover batch read size
       sessionReloadReadSize = 100
       # async, sync
       flushDiskMode = async
     }
   
     ## database store property
     db {
       ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
       datasource = "druid"
       ## mysql/oracle/postgresql/h2/oceanbase etc.
       dbType = "mysql"
       driverClassName = "com.mysql.jdbc.Driver" ##按实际情况修改
       url = "jdbc:mysql://。。。:3306/seata" ##按实际情况修改
       user = "。。。" ##按实际情况修改
       password = "。。。" ##按实际情况修改
       minConn = 5
       maxConn = 30
       globalTable = "global_table"
       branchTable = "branch_table"
       lockTable = "lock_table"
       queryLimit = 100
       maxWait = 5000
     }
   }
   
   service {
     #vgroup->rgroup
     vgroupMapping.my_test_tx_group = "default"
     #only support single node
     default.grouplist = "127.0.0.1:8091"
     #degrade current not support
     enableDegrade = false
     #disable
     disable = false
     #unit ms,s,m,h,d represents milliseconds, seconds, minutes, hours, days, default permanent
     max.commit.retry.timeout = "-1"
     max.rollback.retry.timeout = "-1"
   }
   ```

2. 修改/seata/conf中registry.conf的type为eureka（因为我用的eureka，这里只介绍这种）、

   ```
   registry {
     # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
     type = "eureka"
   
     nacos {
       application = "seata-server"
       serverAddr = "localhost"
       namespace = ""
       cluster = "default"
       username = ""
       password = ""
     }
     eureka {
       serviceUrl = "http://localhost:1001/eureka"
       application = "default"
       weight = "1"
     }
     redis {
       serverAddr = "localhost:6379"
       db = 0
       password = ""
       cluster = "default"
       timeout = 0
     }
     zk {
       cluster = "default"
       serverAddr = "127.0.0.1:2181"
       sessionTimeout = 6000
       connectTimeout = 2000
       username = ""
       password = ""
     }
     consul {
       cluster = "default"
       serverAddr = "127.0.0.1:8500"
     }
     etcd3 {
       cluster = "default"
       serverAddr = "http://localhost:2379"
     }
     sofa {
       serverAddr = "127.0.0.1:9603"
       application = "default"
       region = "DEFAULT_ZONE"
       datacenter = "DefaultDataCenter"
       cluster = "default"
       group = "SEATA_GROUP"
       addressWaitTime = "3000"
     }
     file {
       name = "file.conf"
     }
   }
   
   config {
     # file、nacos 、apollo、zk、consul、etcd3
     type = "file"
   
     nacos {
       serverAddr = "localhost"
       namespace = ""
       group = "SEATA_GROUP"
       username = ""
       password = ""
     }
     consul {
       serverAddr = "127.0.0.1:8500"
     }
     apollo {
       appId = "seata-server"
       apolloMeta = "http://192.168.1.204:8801"
       namespace = "application"
     }
     zk {
       serverAddr = "127.0.0.1:2181"
       sessionTimeout = 6000
       connectTimeout = 2000
       username = ""
       password = ""
     }
     etcd3 {
       serverAddr = "http://localhost:2379"
     }
     file {
       name = "file.conf"
     }
   }
   
   ```

3. 启动eureka。

   ```
   @SpringBootApplication
   @EnableEurekaServer
   public class SpringEurekaApplication {
       public static void main(String[] args) {
           SpringApplication.run(SpringEurekaApplication.class, args);
       }
   }
   ```

4. 启动seata-server.进入/seata/bin。启动seata-server.bat

5. 准备启动user，将file.conf,registry.conf放入user的resources中

6. 配置DataSourceProxy代理

   ```fortran
   @Primary
   @Bean("dataSource")
   public DataSourceProxy dataSource() {
   DruidDataSource dataSource = new DruidDataSource();
   dataSource.setUrl(env.getProperty("spring.datasource.url"));
   dataSource.setUsername(env.getProperty("spring.datasource.username"));
   dataSource.setPassword(env.getProperty("spring.datasource.password"));
   dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
   return new DataSourceProxy(dataSource);
   }
   ```

7. 添加@GlobalTransactional注解到需要开启事务的服务上。

8. 添加order服务，配置如user一样

 做完以上工作，seata的AT模式就正式启用了。



##### 2.TCC模式

​	TCC模式对代码有较强的嵌入性，新拉了个account模块引入TCC代码

​	**业务模块**：order，account

 1. 事务发起方依然通过@GlobalTransactional开启事务，此时事务发起方为order

 2. 在接口上加上@LocalTCC。@LocalTCC适用于SpringCloud+Feign模式下的TCC

 3. @TwoPhaseBusinessAction注解Try方法。name为当前tcc方法的bean名称

    ```java
    @LocalTCC
    public interface AccountAction {
    
    	/**
         * name: tcc的bean的名称，全局需要唯一
         * commitMethod: try成功后的二阶段提交方法
         * rollbackMethod: try失败后的二阶段回滚方法
         * @BusinessActionContextParameter: 可传值到二阶段的参数
         *
         * @param paymentAmount
         * @return
         */
        @TwoPhaseBusinessAction(name = "pay", commitMethod = "commit", rollbackMethod = "fallback")
        public boolean pay(@BusinessActionContextParameter(paramName = "paymentAmount") BigDecimal paymentAmount);
    
        public boolean commit(BusinessActionContext context);
    
        public boolean fallback(BusinessActionContext  context);
    }
    ```

 4. 以上步骤做完，开启SeataTCC配置。





附录1

* 源码启动seata-server服务

  1. 打开server。server模块

     ```java
     public static void main(String[] args) throws IOException {
             //解析器，解析需要的参数
             ParameterParser parameterParser = new ParameterParser(args);
     
             //初始化指标
             MetricsManager.get().init();
     
             //将存储模式放到系统环境变量
             System.setProperty(ConfigurationKeys.STORE_MODE, parameterParser.getStoreMode());
     		
         	//创建RM,TM通讯的rpc服务器
             RpcServer rpcServer = new RpcServer(WORKING_THREADS);
             //创建监听port
             rpcServer.setListenPort(parameterParser.getPort());
         	//初始化id生成器
             UUIDGenerator.init(parameterParser.getServerNode());
             //设置日志存储格式
             SessionHolder.init(parameterParser.getStoreMode());
     		//
             DefaultCoordinator coordinator = new DefaultCoordinator(rpcServer);
             coordinator.init();
             rpcServer.setHandler(coordinator);
             // register ShutdownHook
             ShutdownHook.getInstance().addDisposable(coordinator);
             ShutdownHook.getInstance().addDisposable(rpcServer);
     
             //127.0.0.1 and 0.0.0.0 are not valid here.
             if (NetUtil.isValidIp(parameterParser.getHost(), false)) {
                 XID.setIpAddress(parameterParser.getHost());
             } else {
                 XID.setIpAddress(NetUtil.getLocalIp());
             }
             XID.setPort(rpcServer.getListenPort());
     
             try {
                 rpcServer.init();
             } catch (Throwable e) {
                 LOGGER.error("rpcServer init error:{}", e.getMessage(), e);
                 System.exit(-1);
             }
     
             System.exit(0);
         }
     ```

* 表的建立

  
package com.lc.test.user;

import com.alibaba.druid.pool.DruidDataSource;
import com.lorne.tx.compensate.repository.CompensateDataSource;
import com.lorne.tx.db.LCNDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.lc.test.user.mapper")
@Configuration
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Autowired
    private Environment env;

    @PostConstruct
    public void findAll(){

    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        LCNDataSourceProxy dataSourceProxy = new LCNDataSourceProxy();
        dataSourceProxy.setDataSource(dataSource);
        //分布式事务参与的最大连接数，确保不要超过普通连接池的最大值
        dataSourceProxy.setMaxCount(10);
        return dataSourceProxy;
    }

    @Bean
    public CompensateDataSource compensateDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(5);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        CompensateDataSource compensateDataSource = new CompensateDataSource();
        compensateDataSource.setDataSource(dataSource);

        return compensateDataSource;
    }
}

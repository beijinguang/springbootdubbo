package com.idea4j.configserver;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.idea4j.common.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * springboot集成mybatis的基本入口 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)
 * 2）创建SqlSessionFactory 3）配置事务管理器，除非需要使用事务，否则不用配置
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements EnvironmentAware {

    private Environment environment;

    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }
    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("dBOneDataSource") DataSource dBOneDataSource,
                                        @Qualifier("dBTwoDataSource") DataSource dBTwoDataSource,
                                        @Qualifier("dBThreeDataSource") DataSource dBThreeDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put("db1", dBOneDataSource);
        targetDataSources.put("db2", dBTwoDataSource);
        targetDataSources.put("db3", dBThreeDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(dBTwoDataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource dBOneDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("spring.datasource.driverClassName"));
        props.put("url", environment.getProperty("spring.datasource.url"));
        props.put("username", environment.getProperty("spring.datasource.username"));
        props.put("password", environment.getProperty("spring.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource dBTwoDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("spring.datasource.driverClassName2"));
        props.put("url", environment.getProperty("spring.datasource.url2"));
        props.put("username", environment.getProperty("spring.datasource.username2"));
        props.put("password", environment.getProperty("spring.datasource.password2"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource dBThreeDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("spring.datasource.driverClassName3"));
        props.put("url", environment.getProperty("spring.datasource.url3"));
        props.put("username", environment.getProperty("spring.datasource.username3"));
        props.put("password", environment.getProperty("spring.datasource.password3"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        //fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
        //fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}

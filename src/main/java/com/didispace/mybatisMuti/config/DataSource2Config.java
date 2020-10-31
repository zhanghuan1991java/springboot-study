package com.didispace.mybatisMuti.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Oracle数据源
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.didispace.mybatis",
                            "com.didispace.mybatisMuti.mapper_oracle"},
        sqlSessionTemplateRef  = "oracleSqlSessionTemplate")
public class DataSource2Config {

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //配置目录层次类似的 mapper.xml文件:classpath:oracle_mapper/*/*.xml
            bean.setMapperLocations(resolver.getResources("classpath:oracle_mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            log.error("获取SqlSessionFactory异常",e);
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
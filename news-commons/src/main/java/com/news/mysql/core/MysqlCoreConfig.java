package com.news.mysql.core;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 自动化配置核心数据库的连接配置
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "mysql.core")
@PropertySource("classpath:mysql-core-jdbc.properties")
@MapperScan(basePackages = "com.news.model.mappers", sqlSessionFactoryRef = "mysqlCoreSqlSessionFactory")
public class MysqlCoreConfig {
    String jdbcUrl;
    String jdbcUserName;
    String jdbcPassword;
    String jdbcDriver;
    String rootMapper;//mapper文件在classpath下存放的根路径
    String aliasesPackage;//别名包
    String helperDialect = "mysql";// 分页语言
    Boolean helperReasonable = false;//分页合理化
    Boolean supportMethodsArguments = false;//自动根据上面 params 配置的字段中取值
    String params;//pageNum,pageSize,count,pageSizeZero,reasonable，不配置映射的用默认值， 默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero

    /**
     * 设置数据库连接池
     */
    @Bean("mysqlCoreDataSource")
    public DataSource mysqlCoreDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(this.getJdbcDriver());
        dataSource.setJdbcUrl(this.getJdbcUrl());
        dataSource.setUsername(this.getJdbcUserName());
        // 密码反转处理
        dataSource.setPassword(this.getRealPassword());
        //最大连接数
        dataSource.setMaximumPoolSize(50);
        //最小连接数
        dataSource.setMinimumIdle(5);

        return dataSource;
    }

    /**
     * 这是Mybatis的Session
     *
     * @return
     * @throws IOException
     */
    @Bean("mysqlCoreSqlSessionFactory")
    public SqlSessionFactoryBean mysqlCoreSqlSessionFactory(@Qualifier("mysqlCoreDataSource") DataSource mysqlCoreDataSource) throws IOException {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(mysqlCoreDataSource);

        sessionFactoryBean.setTypeAliasesPackage(this.getAliasesPackage());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources(this.getMapperFilePath()));
        // 开启驼峰标识
        org.apache.ibatis.session.Configuration mybatisConf = new org.apache.ibatis.session.Configuration();
        mybatisConf.setMapUnderscoreToCamelCase(true);
        sessionFactoryBean.setConfiguration(mybatisConf);
        return sessionFactoryBean;
    }

    /**
     * 密码反转，简单示意密码在配置文件中的加密处理
     *
     * @return
     */
    public String getRealPassword() {
        return StringUtils.reverse(this.getJdbcPassword());
    }

    /**
     * 拼接Mapper.xml文件的存放路径
     *
     * @return
     */
    public String getMapperFilePath() {
        return new StringBuffer().append("classpath*:").append(this.getRootMapper()).append("/**/*.xml").toString();
    }


}

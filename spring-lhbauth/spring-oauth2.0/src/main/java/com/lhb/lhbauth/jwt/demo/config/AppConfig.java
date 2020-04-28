package com.lhb.lhbauth.jwt.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
 /**
 * @description:指定token存储在数据库
 * @author: clj
 * @date: 13:52 24/04/2019
 * @version : V1.0
 */
@Configuration
public class AppConfig {
    @Value("${spring.datasource.druid.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.druid.driver-class-name}")
    private String dbDriverClassName;

    @Value("${spring.datasource.druid.username}")
    private String dbUsername;

    @Value("${spring.datasource.druid.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }
}
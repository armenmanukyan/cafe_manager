package com.myCafe.web.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;

@PropertySource("classpath:application.properties")
public abstract class AppConfig {

    @Bean
    @Primary
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public abstract DataSource dataSource(DataSourceProperties properties);

    @Bean
    public abstract JpaTransactionManager transactionManager();

    @Bean
    public abstract CommonsMultipartResolver multipartResolver();

    @Bean
    public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory();

}

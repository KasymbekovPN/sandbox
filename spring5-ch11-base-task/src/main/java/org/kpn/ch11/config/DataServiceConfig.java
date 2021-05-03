package org.kpn.ch11.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "org.kpn.ch11.repos")
@ComponentScan(basePackages = "org.kpn.ch11")
public class DataServiceConfig {
//package com.apress.prospring5.ch11.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//    /**
//     * Created by iuliana.cosmina on 4/29/17.
//     */
//    @Configuration
//    @EnableJpaRepositories(basePackages = {"com.apress.prospring5.ch11.repos"})
//    @ComponentScan(basePackages  = {"com.apress.prospring5.ch11"} )
//    public class DataServiceConfig {
//

    private static final Logger logger = LoggerFactory.getLogger(DataServiceConfig.class);

    @Bean
    public DataSource dataSource(){
        try{
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        } catch (Exception ex){
            logger.error("Embedded DataSource bean can't be created : {}", ex.getMessage());
            return null;
        }
    }

    @Bean
    public Properties hibernateProperties(){
        Properties prop = new Properties();
        prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        prop.put("hibernate.hbm2ddl.auto", "create-drop");
//        prop.put("hibernate.format_sql", true);
        prop.put("hibernate.show_sql", true);
        prop.put("hibernate.max_fetch_depth", 3);
        prop.put("hibernate.jdbc.batch_size", 10);
        prop.put("hibernate.jdbc.fetch_size", 50);

        return prop;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("org.kpn.ch11.entities");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.afterPropertiesSet();

        return factory.getNativeEntityManagerFactory();
    }
}

package org.kpn.ch7.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "org.kpn.ch7")
@EnableTransactionManagement
@PropertySource("classpath:db/jdbc.properties")
public class AdvancedConfig {

    private static final Logger logger = LoggerFactory.getLogger(AdvancedConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${login}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        //<
        logger.info("{} : {} : {} : {}", driverClassName, url, username, password);
        //<
        try{
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(driverClassName);
            ds.setUrl(url);
            ds.setPassword(password);
            ds.setUsername(username);
            return ds;
        } catch (Exception ex){
            logger.error("DBCP datasource bean can't be created : {}", ex.getMessage());
            return null;
        }
    }

    private Properties hibernateProperties(){
        Properties prop = new Properties();
        prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        prop.put("hibernate.hbm2ddl.auto", "create-drop");
        prop.put("hibernate.format_sql", true);
        prop.put("hibernate.use_sql_comments", true);
        prop.put("hibernate.show_sql", true);
        prop.put("hibernate.max_fetch_depth", 3);
        prop.put("hibernate.jdbc.batch_size", 10);
        prop.put("hibernate.jdbc.fetch_size", 50);

        return prop;
    }

    @Bean
    public SessionFactory sessionFactory(){
        logger.info("111");
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("org.kpn.ch7.entities")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean(destroyMethod = "destroy")
    public CleanUp cleanUp(){
        return new CleanUp(new JdbcTemplate(dataSource()));
    }
}

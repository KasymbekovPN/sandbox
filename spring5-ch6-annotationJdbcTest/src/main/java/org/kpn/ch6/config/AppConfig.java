package org.kpn.ch6.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.kpn.ch6.dao.JdbcSingerDao;
import org.kpn.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@PropertySource("db/jdbc.properties")
@ComponentScan(basePackages = "org.kpn.ch6")
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

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

//    @Bean(destroyMethod = "close")
    @Bean(name = "dataSource")
    public DataSource dataSource(){
        try{
            //<
            logger.info("driverClassName: {}", driverClassName);
            logger.info("url: {}", url);
            logger.info("username: {}", username);
            logger.info("password: {}", password);
            //<

            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            return builder.setType(EmbeddedDatabaseType.H2)
                    .addScripts(
                            "classpath:db/h2/schema.sql",
                            "classpath:db/h2/test-data.sql"
                    )
                    .build();

            //<
//            BasicDataSource ds = new BasicDataSource();
//            ds.setDriverClassName(driverClassName);
//            ds.setUrl(url);
//            ds.setUsername(username);
//            ds.setPassword(password);
//
//            return ds;
        } catch (Exception ex){
            logger.error("DBCP dataSource bean can't be created: {}", ex.getMessage());

            return null;
        }
    }

//    @Bean
//    public DataSource dataSource(){
//        try{
//            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//            return builder.setType(EmbeddedDatabaseType.H2)
//                    .addScripts(
//                            "classpath:db/h2/schema.sql",
//                            "classpath:db/h2/test-data.sql"
//                    )
//                    .build();
//        } catch (Exception ex){
//            logger.error("Embedded DataSource bean can't created : {}", ex.getMessage());
//
//            return null;
//        }
//    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(){
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource());
//
//        return jdbcTemplate;
//    }
//
//    @Bean
//    public SingerDao singerDao(){
//        JdbcSingerDao jdbcSingerDao = new JdbcSingerDao();
//        jdbcSingerDao.setJdbcTemplate(jdbcTemplate());
//
//
//        return jdbcSingerDao;
//    }

}

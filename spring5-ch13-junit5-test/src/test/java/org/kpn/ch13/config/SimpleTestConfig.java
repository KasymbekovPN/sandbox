package org.kpn.ch13.config;

import org.kpn.ch13.init.DBInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(
        basePackages = "org.kpn.ch13",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DBInitializer.class)
)
@Profile("test")
public class SimpleTestConfig {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTestConfig.class);

    @Bean
    public DataSource dataSource(){
        try{
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        } catch (Exception ex){
            logger.info("Embedded DataSource bean can't be created : {}", ex.getMessage());
            return null;
        }
    }
}

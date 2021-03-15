package org.kpn.ch6.config;

import org.kpn.ch6.dao.JdbcSingerDao;
import org.kpn.ch6.dao.JdbcSingerDaoNamed;
import org.kpn.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class EmbeddedJdbcConfigNamed {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfigNamed.class);

    @Bean
    public DataSource dataSource(){
        try{
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            return builder.setType(EmbeddedDatabaseType.H2)
                    .addScripts(
                            "classpath:db/h2/schema.sql",
                            "classpath:db/h2/test-data.sql"
                    )
                    .build();
        } catch (Exception ex){
            logger.error("Embedded DataSource bean can't created : {}", ex.getMessage());

            return null;
        }
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        logger.info("-------------------------------");
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public SingerDao singerDao(){
        JdbcSingerDaoNamed dao = new JdbcSingerDaoNamed();
        dao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());

        return dao;
    }
}

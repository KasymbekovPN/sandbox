package org.kpn.ch6.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.kpn.ch6.MySQLErrorCodesTranslator;
import org.kpn.ch6.entities.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcSingerDao implements SingerDao, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("select first_name || ' ' || last_name from singer where id = ?", new Object[]{id}, String.class);
    }

    @Override
    public void insert(Singer singer) {
        throw new NotImplementedException("insert");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null){
            throw new BeanCreationException("Null jdbcTemplate on JdbcSingerDao");
        }
    }
}

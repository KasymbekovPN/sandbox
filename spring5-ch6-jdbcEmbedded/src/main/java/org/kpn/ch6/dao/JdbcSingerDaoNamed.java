package org.kpn.ch6.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.kpn.ch6.entities.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;

public class JdbcSingerDaoNamed implements SingerDao, InitializingBean {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        String sql = "select first_name || ' ' || last_name from singer where id = :singerId";
        final HashMap<String, Object> namedParameters = new HashMap<>() {{
            put("singerId", id);
        }};
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public void insert(Singer singer) {
        throw new NotImplementedException("insert");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (namedParameterJdbcTemplate == null){
            throw new BeanCreationException("Null namedParameterJdbcTemplate on JdbcSingerDaoNamed");
        }
    }
}

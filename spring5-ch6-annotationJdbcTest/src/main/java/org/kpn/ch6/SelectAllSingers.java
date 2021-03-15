package org.kpn.ch6;

import org.kpn.ch6.entities.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllSingers extends MappingSqlQuery<Singer> {

    private static final String SQL_SELECT_ALL_SINGERS = "select id, first_name, last_name, birth_date from singer";
    //<
//    private static final String SQL_SELECT_ALL_SINGERS = "select * from singer";

    public SelectAllSingers(DataSource ds) {
        super(ds, SQL_SELECT_ALL_SINGERS);
    }

    @Override
    protected Singer mapRow(ResultSet resultSet, int i) throws SQLException {
        Singer singer = new Singer();
        singer.setId(resultSet.getLong("id"));
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date"));

        return singer;
    }
}

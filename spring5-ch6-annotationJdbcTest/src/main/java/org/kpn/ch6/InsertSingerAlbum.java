package org.kpn.ch6;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertSingerAlbum extends BatchSqlUpdate {

    private static final String SQL_INSERT_SINGER_ALBUM =
            "insert into album (singer_id, title, release_date)" +
            " values (:singer_id, :title, :release_date)";

    private static final int BATCH_SIZE = 10;

    public InsertSingerAlbum(DataSource ds) {
        super(ds, SQL_INSERT_SINGER_ALBUM);
        super.declareParameter(new SqlParameter("singer_id", Types.INTEGER));
        super.declareParameter(new SqlParameter("title", Types.VARCHAR));
        super.declareParameter(new SqlParameter("release_date", Types.DATE));
        super.setBatchSize(BATCH_SIZE);
    }
}

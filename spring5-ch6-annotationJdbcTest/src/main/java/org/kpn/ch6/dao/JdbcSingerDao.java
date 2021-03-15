package org.kpn.ch6.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.kpn.ch6.*;
import org.kpn.ch6.entities.Album;
import org.kpn.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository("singerDao")
public class JdbcSingerDao implements SingerDao{

    private static final Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);

    private DataSource dataSource;
    private SelectAllSingers selectAllSingers;
    private SelectSingerByFirstName selectSingerByFirstName;
    private UpdateSinger updateSinger;
    private InsertSinger insertSinger;
    private InsertSingerAlbum insertSingerAlbum;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
//        this.insertSingerAlbum = new InsertSingerAlbum(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        HashMap<String, Object> paramMap = new HashMap<>() {{
            put("first_name", firstName);
        }};
        return selectSingerByFirstName.executeByNamedParam(paramMap);
    }

    @Override
    public String findNameById(Long id) {
        throw new NotImplementedException("findNameById");
    }

    @Override
    public String findLastNameById(Long id) {
        throw new NotImplementedException("findLastNameById");
    }

    @Override
    public String findFirstNameById(Long id) {
        throw new NotImplementedException("findFirstNameById");
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        String sql = "SELECT s.id, s.first_name, s.last_name, s.birth_date" +
                ", a.id AS album_id, a.title, a.release_date FROM singer s " +
                "LEFT JOIN album a ON s.id = a.singer_id";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.query(sql, new SingerWithAlbumExtractor());
    }

    @Override
    public void insert(Singer singer) {
        HashMap<String, Object> paramMap = new HashMap<>() {{
            put("first_name", singer.getFirstName());
            put("last_name", singer.getLastName());
            put("birth_date", singer.getBirthDate());

        }};
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        logger.info("New singer inserted with id : {}", singer.getId());
    }

    @Override
    public void update(Singer singer) {
        HashMap<String, Object> paramMap = new HashMap<>() {{
            put("first_name", singer.getFirstName());
            put("last_name", singer.getLastName());
            put("birth_date", singer.getBirthDate());
            put("id", singer.getId());
        }};
        updateSinger.updateByNamedParam(paramMap);
        logger.info("Existing singer updating with id : {}", singer.getId());
    }

    @Override
    public void delete(Singer singer) {
        throw new NotImplementedException("delete");
    }

    @Override
    public void insertWithAlbum(Singer singer) {
        this.insertSingerAlbum = new InsertSingerAlbum(dataSource);
        HashMap<String, Object> paramMap = new HashMap<>() {{
            put("first_name", singer.getFirstName());
            put("last_name", singer.getLastName());
            put("birth_date", singer.getBirthDate());
        }};
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        logger.info("New singer inserted with id : {}", singer.getId());

        List<Album> albums = singer.getAlbums();
        if (albums != null){
            albums.forEach(album -> {
                insertSingerAlbum.updateByNamedParam(new HashMap<String, Object>(){{
                    put("singer_id", singer.getId());
                    put("title", album.getTitle());
                    put("release_date", album.getReleaseDate());
                }});
            });
        }
        insertSingerAlbum.flush();
    }

    private static final class SingerWithAlbumExtractor implements ResultSetExtractor<List<Singer>>{
        @Override
        public List<Singer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            HashMap<Long, Singer> map = new HashMap<>();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                Singer singer = null;
                if (!map.containsKey(id)){
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(resultSet.getString("first_name"));
                    singer.setLastName(resultSet.getString("last_name"));
                    singer.setBirthDate(resultSet.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                else {
                    singer = map.get(id);
                }
                long albumId = resultSet.getLong("album_id");
                if (albumId > 0)
                {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(resultSet.getString("title"));
                    album.setReleaseDate(resultSet.getDate("release_date"));
                    singer.getAlbums().add(album);
                }
            }

            return new ArrayList<>(map.values());
        }
    }
}

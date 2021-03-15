package org.kpn.ch6.dao;

import org.kpn.ch6.ConnectionManager;
import org.kpn.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao implements SingerDao{

    private static final Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);

    @Override
    public List<Singer> findAll() {
        ArrayList<Singer> result = new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionManager.createConnection();

            PreparedStatement statement = connection.prepareStatement("select * from singer");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Singer singer = new Singer();
                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setFirstName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
                result.add(singer);
            }
        } catch (SQLException ex){
            logger.error("Problem when executing SELECT", ex);
        } finally {
            ConnectionManager.closeConnection(connection);
        }

        return result;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {
        Connection connection = null;
        try{
            connection = ConnectionManager.createConnection();
            PreparedStatement statement = connection.prepareStatement("insert into singer (first_name, last_name, birth_date) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                singer.setId(generatedKeys.getLong(1));
            }
            statement.close();
        } catch(SQLException ex){
            logger.error("Problem executing INSERT", ex);
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {
        Connection connection = null;
        try{
            connection = ConnectionManager.createConnection();
            PreparedStatement statement = connection.prepareStatement("delete from singer where id=?");
            statement.setLong(1, singerId);
            statement.execute();
            statement.close();
        } catch (SQLException ex){
            logger.error("Problem executing DELETE: ", ex);
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public List<Singer> findAllWithDetail() {
        return null;
    }

    @Override
    public void insertWithDetail(Singer singer) {

    }
}

package org.kpn.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    private static final String path = "C:\\projects";
    private static final String dbName = "testdb";
    private static final String login = "root";
    private static final String password = "root";

    public static Connection createConnection(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:" + path + "/" + dbName, login, password);
        } catch (Exception ex){
            logger.error("Problem connection creation : {}", ex.getMessage());
        }

        logger.info("Connection created : {}", connection);
        return connection;
    }

    public static void closeConnection(Connection connection){
        if (connection == null){
            logger.warn("Connection is null");
            return;
        }

        try{
            connection.close();
            logger.info("Connection is closed");
        } catch (SQLException ex){
            logger.error("Problem connection closing : {}", ex.getMessage());
        }
    }

    public static void fill() throws SQLException {
        Connection connection = createConnection();

        Statement statement = connection.createStatement();
        String sql = "create table if not exists singer("
                +"id int not null auto_increment,"
                +"first_name varchar(60) not null,"
                +"last_name varchar(40) not null,"
                +"birth_date date,"
                +"unique uq_singer_1 (first_name, last_name),"
                +"primary key (id)"
                +");";
        statement.executeUpdate(sql);

        sql = "create table if not exists album("
                +"id int not null auto_increment,"
                +"singer_id int not null,"
                +"title varchar(100) not null,"
                +"release_date date,"
                +"unique uq_singer_album_1 (singer_id, title),"
                +"primary key (id),"
                +"constraint fk_album foreign key (singer_id) references singer (id)"
                +");";
        statement.executeUpdate(sql);

        closeConnection(connection);
    }
}

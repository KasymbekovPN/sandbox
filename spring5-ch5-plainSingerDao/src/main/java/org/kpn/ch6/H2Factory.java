package org.kpn.ch6;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2Factory {

    public static Connection createH2DB(String path, String dbname, String username, String password){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:" + path + "/" + dbname, username, password);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return connection;
    }

    public static Connection createH2DB(String dbname, String username, String password){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:mem:" + dbname, username, password);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return  connection;
    }
}

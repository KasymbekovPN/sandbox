package org.kpn.ch6;

import org.kpn.ch6.dao.PlainSingerDao;
import org.kpn.ch6.dao.SingerDao;
import org.kpn.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class PlainJdbcDemo {

    private static final Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);
    private static final SingerDao singerDao = new PlainSingerDao();




    public static void main(String[] args) throws SQLException {

        ConnectionManager.fill();

        logger.info("Listing initial singer data");
        listAllSingers();
        logger.info("-------------");
        logger.info("Insert a new singer");

        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(
                new Date((new GregorianCalendar(1991, Calendar.MARCH, 19)).getTimeInMillis())
        );
        singerDao.insert(singer);
        listAllSingers();

//        logger.info("-------------");
//        logger.info("Deleting the previous created singer");
//        singerDao.delete(singer.getId());
//        listAllSingers();
    }

    private static void listAllSingers(){
        List<Singer> singers = singerDao.findAll();
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    //<
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import h2.H2Factory;
//
//    public class Main {
//
//        public static void main(String[] args) {
//
//            try {
//
//                //Create in-memory database and open connection
//                Connection h2DBConnection = H2Factory.createH2Database("/Users/mohmair/Documents/GitHub/H2", "My_DB", "", "");
//
//                //Initialize Statement
//                Statement h2BDStatement = h2DBConnection.createStatement();
//
//                //Create a table
//                //h2BDStatement.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMERS (ID INT NOT NULL, NAME VARCHAR (20) NOT NULL)");
//
//                h2BDStatement.executeUpdate("CREATE TABLE DRM_MAPPINGS AS SELECT * FROM CSVREAD('DRM_MP09.20190325')");
//
//
//
//                //Insert some records
//                //h2BDStatement.executeUpdate("insert into CUSTOMERS values(1,'MOHIT')");
//                //h2BDStatement.executeUpdate("insert into CUSTOMERS values(1,'MOHIT')");
//                //h2BDStatement.executeUpdate("insert into CUSTOMERS values(1,'MOHIT')");
//
//                //Get recordset
//                ResultSet h2DBResultset = h2BDStatement.executeQuery("Select count(*) as COUNT from CUSTOMERS");
//
//                //Print count
//                while (h2DBResultset.next()) {
//                    System.out.println(h2DBResultset.getInt(1));
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
}

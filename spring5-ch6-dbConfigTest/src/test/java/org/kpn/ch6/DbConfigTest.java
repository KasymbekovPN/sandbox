package org.kpn.ch6;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(DbConfigTest.class);

    @Test
    public void testOne() throws SQLException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("datasource-cfg-01.xml");

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        Assert.assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testTwo() throws SQLException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("datasource-cfg-02.xml");

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        Assert.assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testThree() throws SQLException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        Assert.assertNotNull(dataSource);
        testDataSource(dataSource);

        ctx.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int mockInt = resultSet.getInt("1");
                Assert.assertEquals(1, mockInt);
            }
            preparedStatement.close();
        } catch (Exception ex) {
            logger.debug("Sth unexpected happened: {}", ex.getMessage());
        }
    }
}

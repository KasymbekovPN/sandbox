package org.kpn.ch13;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.kpn.ch13.config.SimpleTestConfig;
import org.kpn.ch13.config.DataConfig;
import org.kpn.ch13.config.ServiceConfig;
import org.kpn.ch13.entities.Singer;
import org.kpn.ch13.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = {
        SimpleTestConfig.class,
        ServiceConfig.class,
        DataConfig.class
})
@DisplayName("Integration Singer Service Test")
@ActiveProfiles("test")
public class SingerServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(SingerServiceTest.class);

    @Autowired
    SingerService singerService;

    @BeforeAll
    static void setUp(){
        logger.info("--> @BeforeAll - executes before executing all test methods in this class");
    }

    @AfterAll
    static void tearDown(){
        logger.info("--> @AfterAll - executes after executing a;; test methods in this class");
    }

    @BeforeEach
    void init(){
        logger.info("--> @BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void dispose(){
        logger.info("--> @AfterEach - executes after each test method in this class");
    }

    @Test
    @DisplayName("should return all singers")
    @SqlGroup({
            @Sql(
                    value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
            ),
            @Sql(
                    value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
            )
    })
    public void findAll(){
        List<Singer> singers = singerService.findAll();
        logger.info("singers: {}", singers);
        Assert.assertNotNull(singers);
        Assert.assertEquals(1, singers.size());
    }

    @Test
    @DisplayName("should return singer 'John Mayer'")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    public void testFindByFirstNameAndLastNameOne() throws Exception {
        Singer result = singerService.findByFirstNameAndLastName("John", "Mayer");
        Assert.assertNotNull(result);
    }
}

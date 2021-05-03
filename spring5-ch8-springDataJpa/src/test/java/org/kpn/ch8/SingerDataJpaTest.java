package org.kpn.ch8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kpn.ch8.config.DataJpaConfig;
import org.kpn.ch8.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SingerDataJpaTest {

    private static final Logger logger = LoggerFactory.getLogger(SingerDataJpaTest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        Assert.assertEquals(3, singers.size());
        listSingers(singers);
    }
//        @Test
//        public void testFindAll(){
//            List<Singer> singers = singerService.findAll();
//            assertTrue(singers.size() > 0);
//            listSingers(singers);
//        }
//
//        @Test
//        public void testFindByFirstName(){
//            List<Singer> singers = singerService.findByFirstName("John");
//            assertTrue(singers.size() > 0);
//            assertEquals(2, singers.size());
//            listSingers(singers);
//        }
//
//        @Test
//        public void testFindByFirstNameAndLastName(){
//            List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
//            assertTrue(singers.size() > 0);
//            assertEquals(1, singers.size());
//            listSingers(singers);
//        }
//
    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        Assert.assertNotNull(singerService);
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}

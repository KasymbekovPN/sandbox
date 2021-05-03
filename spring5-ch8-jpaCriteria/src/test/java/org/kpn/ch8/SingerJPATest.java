package org.kpn.ch8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kpn.ch8.config.JpaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SingerJPATest {

    private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);
    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean("jpaSingerService", SingerService.class);
        Assert.assertNotNull(singerService);
    }

    @Test
    public void testFindByCriteriaQuery() {
        List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
        Assert.assertEquals(1, singers.size());
        listSingersWithAlbum(singers);
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        singers.forEach(s -> {
            logger.info(s.toString());
            if (s.getAlbums() != null) {
                s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
            }
            if (s.getInstruments() != null) {
                s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
            }
        });
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}

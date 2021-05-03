package org.kpn.ch8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kpn.ch8.config.JpaConfig;
import org.kpn.ch8.entities.Singer;
import org.kpn.ch8.service.SingerSummaryService;
import org.kpn.ch8.service.SingerSummaryUntypeImpl1;
import org.kpn.ch8.view.SingerSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SingerSummaryJpaTest {

    private static final Logger logger = LoggerFactory.getLogger(SingerSummaryJpaTest.class);

    private GenericApplicationContext ctx;
    private SingerSummaryUntypeImpl1 singerSummaryUntype;
    private SingerSummaryService singerSummaryService;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl1.class);
        singerSummaryService = ctx.getBean(SingerSummaryService.class);
    }

    @Test
    public void testFindAllUntype1(){
        singerSummaryUntype.displayAllSingerSummary();
    }

    @Test
    public void testFindAll(){
        List<SingerSummary> singers = singerSummaryService.findAll();
        Assert.assertNotNull(singers);
        listSingers(singers);
    }

    private static void listSingers(List<SingerSummary> singers){
        logger.info(" --- Listing singers");
        for (SingerSummary singer : singers) {
            logger.info("{}", singer);
        }
    }

    @After
    public void tearDown(){
        ctx.close();
    }
}

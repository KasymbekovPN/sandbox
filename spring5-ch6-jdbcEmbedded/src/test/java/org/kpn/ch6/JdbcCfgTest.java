package org.kpn.ch6;

import org.junit.Assert;
import org.junit.Test;
import org.kpn.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcCfgTest {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCfgTest.class);

    @Test
    public void testH2(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring/embedded-h2-cfg.xml");

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        testDao(singerDao);

        ctx.close();;
    }

    private void testDao(SingerDao singerDao){
        Assert.assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        logger.info("singerName: {}", singerName);
        Assert.assertEquals(singerName, "John Mayer");
    }
}

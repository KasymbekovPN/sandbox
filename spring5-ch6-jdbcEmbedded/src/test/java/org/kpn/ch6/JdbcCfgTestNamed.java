package org.kpn.ch6;

import org.junit.Assert;
import org.junit.Test;
import org.kpn.ch6.config.EmbeddedJdbcConfigNamed;
import org.kpn.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcCfgTestNamed {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCfgTestNamed.class);

    @Test
    public void testH2(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfigNamed.class);

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        testDao(singerDao);

        ctx.close();
    }

    private void testDao(SingerDao singerDao){
        Assert.assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        logger.info("singerName: {}", singerName);
        Assert.assertEquals(singerName, "John Mayer");
    }
}

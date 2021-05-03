package org.kpn.ch13;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kpn.ch13.config.DataConfig;
import org.kpn.ch13.config.ServiceConfig;
import org.kpn.ch13.config.ServiceTestConfig;
import org.kpn.ch13.entities.Singer;
import org.kpn.ch13.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        ServiceTestConfig.class,
        ServiceConfig.class,
        DataConfig.class
})
@TestExecutionListeners(ServiceTestExecutionListener.class)
@ActiveProfiles("test")
public class SingerServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(SingerServiceImplTest.class);

    @Autowired
    SingerService singerService;

    @PersistenceContext
    private EntityManager em;

    @DataSets(setUpDataSet = "/org/kpn/ch13/SingerServiceImplTest.xls")
    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        logger.info("singers: {}", singers);

        Assert.assertNotNull(singers);
        Assert.assertEquals(1, singers.size());
    }

    //<
//        @DataSets(setUpDataSet= "/com/apress/prospring5/ch13/SingerServiceImplTest.xls")
//        @Test
//        public void testFindByFirstNameAndLastNameTwo() throws Exception {
//            Singer result = singerService.findByFirstNameAndLastName("BB", "King");
//            assertNull(result);
//        }
//
//        @Test
//        public void testAddSinger() throws Exception {
//            deleteFromTables("SINGER");
//
//            Singer singer = new Singer();
//            singer.setFirstName("Stevie");
//            singer.setLastName("Vaughan ");
//
//            singerService.save(singer);
//            em.flush();
//
//            List<Singer> singers = singerService.findAll();
//            assertEquals(1, singers.size());
//        }
//
//        @Test(expected=AssertionError.class)
//        public void testAddSingerWithJSR349Error() throws Exception {
//            deleteFromTables("SINGER");
//
//            Singer singer = new Singer();
//
//            singerService.save(singer);
//            em.flush();
//
//            List<Singer> singers = singerService.findAll();
//            assertEquals(0, singers.size());
//        }
//    }
}

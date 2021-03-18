package org.kpn.ch7;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kpn.ch7.config.AppConfig;
import org.kpn.ch7.dao.SingerDao;
import org.kpn.ch7.entities.Album;
import org.kpn.ch7.entities.Instrument;
import org.kpn.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class SingerDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(SingerDaoTest.class);

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public  void setUp(){
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        Assert.assertNotNull(singerDao);
    }

    @Test
    public void testInsert(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                new GregorianCalendar(1940, Calendar.SEPTEMBER, 16).getTimeInMillis()
        ));

        Album album = new Album();
        album.setTitle("My kind of blues");
        album.setReleaseDate(new Date(
                new GregorianCalendar(1961, 7, 18).getTimeInMillis()
        ));
        singer.addAlbum(album);
        album = new Album();
        album.setTitle("A heart full of blues");
        album.setReleaseDate(new Date(
                new GregorianCalendar(1962, 7, 18).getTimeInMillis()
        ));
        singer.addAlbum(album);

        singerDao.save(singer);
        Assert.assertNotNull(singer.getId());

        List<Singer> singers = singerDao.findAllWithAlbum();
        Assert.assertEquals(4, singers.size());
        listSingerWithAlbum(singers);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerDao.findAll();
        Assert.assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindWithAlbum(){
        List<Singer> singers = singerDao.findAllWithAlbum();
        Assert.assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindById(){
        Singer singer = singerDao.findById(1L);
        Assert.assertNotNull(singer);
        logger.info("{}", singer);
    }

    @Test
    public void testUpdate(){
        Singer singer = singerDao.findById(1L);
        Assert.assertNotNull(singer);
        Assert.assertEquals("Mayer", singer.getLastName());

        Optional<Album> maybeAlbum = singer.getAlbums().stream()
                .filter(a -> a.getTitle().equals("Battle Studies"))
                .findFirst();
        Assert.assertTrue(maybeAlbum.isPresent());
        Album album = maybeAlbum.get();

        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerDao.save(singer);

        listSingerWithAlbum(singerDao.findAllWithAlbum());
    }

    @Test
    public void testDelete(){
        Singer singer = singerDao.findById(2L);
        Assert.assertNotNull(singer);
        singerDao.delete(singer);
        listSingerWithAlbum(singerDao.findAllWithAlbum());
    }

    private static void listSingers(List<Singer> singers){
        logger.info("----- Listing singers:");
        for (Singer singer : singers) {
            logger.info("{}", singer);
        }
    }

    private static void listSingerWithAlbum(List<Singer> singers){
        logger.info("----- Listing singers with instruments");
        for (Singer singer : singers) {
            logger.info("---");
            logger.info("{}", singer);
            if (null != singer.getAlbums()){
                for (Album album : singer.getAlbums()) {
                    logger.info("\tA: {}", album);
                }
            }
            if (null != singer.getInstruments()){
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\tI: {}", instrument);
                }
            }
        }
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}

package org.kpn.ch8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kpn.ch8.config.JpaConfig;
import org.kpn.ch8.entities.Album;
import org.kpn.ch8.entities.Instrument;
import org.kpn.ch8.entities.Singer;
import org.kpn.ch8.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SingerJpaTest {

    private static final Logger logger = LoggerFactory.getLogger(SingerJpaTest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        Assert.assertNotNull(singerService);
    }

    @Test
    public  void testFindAll(){
        List<Singer> singers = singerService.findAll();
        Assert.assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum(){
        List<Singer> singers = singerService.findAllWithAlbum();
        Assert.assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testFindById(){
        Singer singer = singerService.findById(1L);
        Assert.assertNotNull(singer);
        logger.info("{}", singer);
    }

    @Test
    public void testInsert(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAlbum(album);

        singerService.save(singer);
        Assert.assertNotNull(singer.getId());

        List<Singer> singers = singerService.findAllWithAlbum();
        Assert.assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate(){
        Singer singer = singerService.findById(1L);
        //making sure such singer exists
        Assert.assertNotNull(singer);
        //making sure we got expected record
        Assert.assertEquals("Mayer", singer.getLastName());
        //retrieve the album
        Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();

        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerService.save(singer);

        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void testDelete(){
        Singer singer = singerService.findById(2L);
        Assert.assertNotNull(singer);
        singerService.delete(singer);
        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void findAllByNativeQuery(){
        List<Singer> singers = singerService.findAllByNativeQuery();
        Assert.assertEquals(3, singers.size());
        listSingers(singers);
    }

    private static void listSingers(List<Singer> singers){
        logger.info(" --- Listing singers");
        for (Singer singer : singers) {
            logger.info("{}", singer);
        }
    }

    private static void listSingersWithAlbum(List<Singer> singers){
        logger.info(" ----- Listing singers with albums ");
        for (Singer singer : singers) {
            logger.info("-----");
            logger.info("{}", singer);
            if (null != singer.getAlbums()){
                for (Album album : singer.getAlbums()) {
                    logger.info("\ta: {}", album);
                }
            }
            if (null != singer.getInstruments()){
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\ti: {}", instrument);
                }
            }
        }
    }

    @After
    public void tearDown(){
        ctx.close();
    }
}

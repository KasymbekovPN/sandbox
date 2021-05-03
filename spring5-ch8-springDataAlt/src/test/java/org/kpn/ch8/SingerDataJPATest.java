package org.kpn.ch8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kpn.ch8.config.DataJpaConfig;
import org.kpn.ch8.entities.Album;
import org.kpn.ch8.entities.Singer;
import org.kpn.ch8.services.AlbumService;
import org.kpn.ch8.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SingerDataJPATest {

    private static final Logger logger = LoggerFactory.getLogger(SingerDataJPATest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;
    private AlbumService albumService;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        albumService = ctx.getBean(AlbumService.class);
        Assert.assertNotNull(singerService);
        Assert.assertNotNull(albumService);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        Assert.assertTrue(singers.size() > 0);
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName(){
        List<Singer> singers = singerService.findByFirstName("John");
        Assert.assertTrue(singers.size() > 0);
        Assert.assertEquals(2, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByFirstNameAndLastName(){
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        Assert.assertTrue(singers.size() > 0);
        Assert.assertEquals(1, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindBySinger(){
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        Assert.assertTrue(singers.size() > 0);
        Assert.assertEquals(1, singers.size());

        Singer singer = singers.get(0);
        List<Album> albums = albumService.findBySinger(singer);
        Assert.assertTrue(albums.size() > 0);
        Assert.assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString()));
    }

    @Test
    public void testFindByTitle(){
        List<Album> albums = albumService.findByTitle("The");
        Assert.assertTrue(albums.size() > 0);
        Assert.assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString() + ", Singer: "+ a.getSinger().getFirstName() + " "
                + a.getSinger().getLastName()));
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        singers.forEach(s-> logger.info(s.toString()));
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}

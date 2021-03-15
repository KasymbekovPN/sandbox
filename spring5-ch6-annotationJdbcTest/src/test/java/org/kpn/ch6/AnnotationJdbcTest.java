package org.kpn.ch6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kpn.ch6.config.AppConfig;
import org.kpn.ch6.dao.SingerDao;
import org.kpn.ch6.entities.Album;
import org.kpn.ch6.entities.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AnnotationJdbcTest {

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setup() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        Assert.assertNotNull(singerDao);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerDao.findAll();
        Assert.assertEquals(singers.size(), 3);
        singers.forEach(this::listSingers);
        ctx.close();
    }

    @Test
    public void testFindByFirstName(){
        List<Singer> singers = singerDao.findByFirstName("John");
        Assert.assertEquals(singers.size(), 2);
        singers.forEach(this::listSingers);
        ctx.close();
    }

    @Test
    public void testSingerUpdate(){
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                new GregorianCalendar(1977, Calendar.JULY, 16).getTimeInMillis()
        ));
        singerDao.update(singer);
        List<Singer> singers = singerDao.findAll();
        singers.forEach(this::listSingers);
    }

    @Test
    public void testSingerInsert(){
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date(
                new GregorianCalendar(1991, Calendar.FEBRUARY, 17).getTimeInMillis()
        ));
        singerDao.insert(singer);
        List<Singer> singers = singerDao.findAll();
        singers.forEach(this::listSingers);
    }

    @Test
    public void testSingerInsertWithAlbum(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                new GregorianCalendar(1940, Calendar.SEPTEMBER, 16).getTimeInMillis()
        ));
        Album album = new Album();
        album.setTitle("My king of blues");
        album.setReleaseDate(new Date(
                new GregorianCalendar(1961, 7, 18).getTimeInMillis()
        ));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A heart full of blues");
        album.setReleaseDate(new Date(
                new GregorianCalendar(1962, 3, 20).getTimeInMillis()
        ));
        singer.addAlbum(album);
        singerDao.insertWithAlbum(singer);

        List<Singer> singers = singerDao.findAllWithAlbums();
        singers.forEach(this::listSingers);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    private void listSingers(Singer singer){
        System.out.println(singer);
        if (singer.getAlbums() != null){
            for (Album album : singer.getAlbums()) {
                System.out.println("\t--> " + album);
            }
        }
    }
}

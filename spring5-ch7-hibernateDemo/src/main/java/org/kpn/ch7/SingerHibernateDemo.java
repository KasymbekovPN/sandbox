package org.kpn.ch7;

import org.kpn.ch7.config.AppConfig;
import org.kpn.ch7.dao.SingerDao;
import org.kpn.ch7.entities.Album;
import org.kpn.ch7.entities.Instrument;
import org.kpn.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SingerHibernateDemo {

    private static final Logger logger = LoggerFactory.getLogger(SingerHibernateDemo.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingerDao singerDao = ctx.getBean(SingerDao.class);

//        listSingers(singerDao.findAll());
//        listSingersWithAlbum(singerDao.findAllWithAlbum());
        logger.info("{}", singerDao.findById(2L));

        ctx.close();
    }

    private static void listSingers(List<Singer> singers){
        logger.info("---- Listing singers:");
        singers.forEach(singer -> logger.info("{}", singer));
    }

    private static void listSingersWithAlbum(List<Singer> singers){
        logger.info("---- Listing singers with instruments:");
        for (Singer singer : singers) {
            logger.info("---");
            logger.info("{}", singer);
            if (null != singer.getAlbums()){
                for (Album album : singer.getAlbums()) {
                    logger.info("a: {}", album);
                }
            }
            if (null != singer.getInstruments()){
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("i: {}", instrument);
                }
            }
            logger.info("---");
        }
    }
}

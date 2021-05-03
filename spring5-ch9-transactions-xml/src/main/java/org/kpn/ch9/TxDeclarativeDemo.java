package org.kpn.ch9;

import org.kpn.ch9.config.DataJpaConfig;
import org.kpn.ch9.entities.Singer;
import org.kpn.ch9.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TxDeclarativeDemo {

    private static final Logger logger = LoggerFactory.getLogger(TxDeclarativeDemo.class);

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring/tx-declarative-app-context.xml");

        SingerService singerService = ctx.getBean(SingerService.class);

        List<Singer> singers = singerService.findAll();
        logger.info("---------------");
        singers.forEach(s -> logger.info("{}", s));

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singerService.save(singer);

        singers = singerService.findAll();
        logger.info("---------------");
        singers.forEach(s -> logger.info("{}", s));

        logger.info("---------------");
        logger.info("count: {}", singerService.countAll());


        ctx.close();
    }
}

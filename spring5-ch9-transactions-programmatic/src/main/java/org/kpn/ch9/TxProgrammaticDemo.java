package org.kpn.ch9;

import org.kpn.ch9.config.DataJpaConfig;
import org.kpn.ch9.config.ServicesConfig;
import org.kpn.ch9.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TxProgrammaticDemo {

    private static final Logger logger = LoggerFactory.getLogger(TxProgrammaticDemo.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);

        SingerService singerService = ctx.getBean(SingerService.class);
        logger.info("Singer count : {}", singerService.countAll());

        ctx.close();
    }
}

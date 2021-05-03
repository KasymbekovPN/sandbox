package org.kpn.ch8;

import org.kpn.ch8.config.AuditConfig;
import org.kpn.ch8.entities.SingerAudit;
import org.kpn.ch8.services.SingerAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpringAuditJPADemo {

    private static final Logger logger = LoggerFactory.getLogger(SpringAuditJPADemo.class);

    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring/app-context.annotation.xml");
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AuditConfig.class);

        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);
        List<SingerAudit> singers = singerAuditService.findAll();
        listSingers(singers);

        System.out.println("");
        logger.info("Add new singer");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        singerAuditService.save(singer);
        singers = singerAuditService.findAll();
        listSingers(singers);

        singer = singerAuditService.findById(4L);
        System.out.println("");
        logger.info("Singer with id 4 : {}", singer);
        System.out.println("");

        logger.info("Update singer");
        singer.setFirstName("Riley B.");
        singerAuditService.save(singer);
        singers = singerAuditService.findAll();
        listSingers(singers);

        ctx.close();
    }

    private static void listSingers(List<SingerAudit> singers) {
        logger.info(" ---- Listing singers:");
        singers.forEach(s -> logger.info(s.toString()));
    }
}

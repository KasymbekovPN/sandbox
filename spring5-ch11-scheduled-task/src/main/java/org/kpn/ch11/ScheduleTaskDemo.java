package org.kpn.ch11;

import org.kpn.ch11.config.AppConfig;
import org.kpn.ch11.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ScheduleTaskDemo {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTaskDemo.class);

    public static void main(String[] args) throws IOException, InterruptedException {
//        m0();
        m1();
    }

    private static void m1() throws InterruptedException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        CarService carService = ctx.getBean("carService", CarService.class);

        while (!carService.isDone()){
            logger.info("Waiting for scheduled job to end...");
            Thread.sleep(1);
        }

        ctx.close();
    }

    private static void m0() throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        System.in.read();
        ctx.close();
    }
}

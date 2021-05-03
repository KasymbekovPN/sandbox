package org.kpn.ch11;

import org.kpn.ch11.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ScheduleTaskAnnotationDemo {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.in.read();
        ctx.close();
    }
}

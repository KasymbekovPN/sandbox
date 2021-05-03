package org.kpn.ch10;

import org.kpn.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConverterServiceDemo {

    private static final Logger logger = LoggerFactory.getLogger(ConverterServiceDemo.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);
        logger.info("Singer info: {}", john);

        ctx.close();
    }
}

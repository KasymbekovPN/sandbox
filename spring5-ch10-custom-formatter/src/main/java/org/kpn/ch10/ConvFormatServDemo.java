package org.kpn.ch10;

import org.kpn.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServDemo {

    private static final Logger logger = LoggerFactory.getLogger(ConvFormatServDemo.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);
        logger.info("Singer : {}", john);

        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        String convert = conversionService.convert(john.getBirthDate(), String.class);
        logger.info("str: {}", convert);

        ctx.close();
    }
}

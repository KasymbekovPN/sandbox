package org.kpn.ch10;

import org.kpn.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class MultipleConvServDemo {

    private static final Logger logger = LoggerFactory.getLogger(MultipleConvServDemo.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);
        logger.info("john : {}", john);

        ConversionService conversionService = ctx.getBean(ConversionService.class);

        AnotherSinger anotherSinger = conversionService.convert(john, AnotherSinger.class);
        logger.info("another singer : {}", anotherSinger);

        String[] strArray = conversionService.convert("a,b,c", String[].class);
        logger.info("string array: {}", (Object) strArray);

        ArrayList<String> listString = new ArrayList<>() {{
            add("d");
            add("e");
            add("f");
        }};
        HashSet<String> setString = conversionService.convert(listString, HashSet.class);
        logger.info("setString: {}", setString);


        ctx.close();;
    }
}

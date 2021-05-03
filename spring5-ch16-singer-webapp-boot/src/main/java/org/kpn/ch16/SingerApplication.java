package org.kpn.ch16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class SingerApplication {

    private static final Logger logger = LoggerFactory.getLogger(SingerApplication.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SingerApplication.class);
        assert ctx != null;
        logger.info("Application started...");

        System.in.read();
        ctx.close();
    }
}

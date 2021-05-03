package org.kpn.ch15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableMBeanExport;

import java.io.IOException;

@EnableMBeanExport
@SpringBootApplication(scanBasePackages = "org.kpn.ch15")
public class JmxBootApplication {

    private static final Logger logger = LoggerFactory.getLogger(JmxBootApplication.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(JmxBootApplication.class);
        assert ctx != null;
        logger.info("Started...");

        System.in.read();
        ctx.close();
    }
}

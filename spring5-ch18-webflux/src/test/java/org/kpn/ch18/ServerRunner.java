package org.kpn.ch18;

import org.apache.catalina.LifecycleException;
import org.kpn.ch18.web.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ServerRunner {

    private static final Logger logger = LoggerFactory.getLogger(ServerRunner.class);

    public static void main(String[] args) throws IOException, LifecycleException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ServerConfig.class);
        Server server = ctx.getBean(Server.class);
        server.startTomcatServer();
        logger.info("Application started...");

        System.in.read();
        ctx.close();
    }
}

package org.kpn.ch18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.IOException;

public class FileWatcherDemo {

    private static final Logger logger = LoggerFactory.getLogger(FileWatcherDemo.class);

    public static void main(String[] args) throws IOException {
        GenericXmlApplicationContext ctx
                = new GenericXmlApplicationContext("/spring/singerJob.xml");
        assert ctx != null;
        logger.info("Application started...");
        System.in.read();
        ctx.close();
    }
}

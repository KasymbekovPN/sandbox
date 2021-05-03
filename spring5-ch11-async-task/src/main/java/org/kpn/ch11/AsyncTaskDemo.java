package org.kpn.ch11;

import org.kpn.ch11.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTaskDemo {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskDemo.class);

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);
        for (int i = 0; i < 5; i++) {
            asyncService.asyncTask();
        }

        Future<String> result0 = asyncService.asyncWithReturn("John Mayer");
        Future<String> result1 = asyncService.asyncWithReturn("Eric Clapton");
        Future<String> result2 = asyncService.asyncWithReturn("BB King");

        Thread.sleep(6_000);

        logger.info("Result0: {}", result0.get());
        logger.info("Result1: {}", result1.get());
        logger.info("Result2: {}", result2.get());

        System.in.read();
        ctx.close();
    }
}

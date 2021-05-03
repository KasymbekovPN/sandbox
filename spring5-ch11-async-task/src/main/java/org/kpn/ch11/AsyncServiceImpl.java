package org.kpn.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService{

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public void asyncTask() {
        logger.info("Start execution of asycn. task");

        try{
            Thread.sleep(10_000);
        } catch (Exception ex){
            logger.error("Task interruption : {}", ex.getMessage());
        }

        logger.info("Complete execution of async. task");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        logger.info("Start execution of async. task with return for "+ name);

        try{
            Thread.sleep(5_000);
        } catch (Exception ex){
            logger.error("Task interruption: {}", ex.getMessage());
        }

        logger.info("Complete execution of async. task with return for {}", name);
        return new AsyncResult<>("Hello: " + name);
    }
}

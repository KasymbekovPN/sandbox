package org.kpn.ch18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {

    private static final Logger logger = LoggerFactory.getLogger(SingerItemProcessor.class);

    @Override
    public Singer process(Singer singer) throws Exception {
        final Singer transformedSinger = new Singer();
        transformedSinger.setFirstName(singer.getFirstName().toUpperCase());
        transformedSinger.setLastName(singer.getLastName().toUpperCase());
        transformedSinger.setSong(singer.getSong().toUpperCase());

        logger.info("Transformed singer : {} into {}", singer, transformedSinger);
        return transformedSinger;
    }
}

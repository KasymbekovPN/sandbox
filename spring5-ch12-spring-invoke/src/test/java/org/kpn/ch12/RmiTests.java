package org.kpn.ch12;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kpn.ch12.config.RmiClientConfig;
import org.kpn.ch12.entities.Singer;
import org.kpn.ch12.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//@Ignore
@ContextConfiguration(classes = RmiClientConfig.class)
@RunWith(SpringRunner.class)
public class RmiTests {

    private static final Logger logger = LoggerFactory.getLogger(RmiTests.class);

    @Autowired
    private SingerService singerService;

    @Test
    public void testRmiAll(){
        List<Singer> singers = singerService.findAll();
        Assert.assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testRmiJohn(){
        List<Singer> singers = singerService.findByFirstName("John");
        Assert.assertEquals(2, singers.size());
        listSingers(singers);
    }

    private void listSingers(List<Singer> singers){
        singers.forEach(s -> logger.info(s.toString()));
    }
}

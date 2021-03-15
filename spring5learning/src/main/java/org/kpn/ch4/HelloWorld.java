package org.kpn.ch4;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public void sayHi(){
        logger.info("Hello world!");
    }
}

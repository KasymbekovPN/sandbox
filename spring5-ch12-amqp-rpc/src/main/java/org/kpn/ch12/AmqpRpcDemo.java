package org.kpn.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AmqpRpcDemo {

    private static final Logger logger = LoggerFactory.getLogger(AmqpRpcDemo.class);

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring/amqp-rpc-app-context.xml");

        WeatherService ws = ctx.getBean(WeatherService.class);
        logger.info("Forecast for {} : {}", "FL", ws.getForecast("FL"));
        logger.info("Forecast for {} : {}", "MA", ws.getForecast("MA"));
        logger.info("Forecast for {} : {}", "CA", ws.getForecast("CA"));

        ctx.close();
    }
}

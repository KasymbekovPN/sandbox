package org.kpn.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @RabbitListener(queues = "forecasts")
    public void getForecast(String stateCode){
        switch (stateCode){
            case "FL":
                logger.info("HOT");
                break;
            case "MA":
                logger.info("COLD");
                break;
            default:
                logger.warn("Not available at this time");
                break;
        }
    }
}

package org.kpn.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class WeatherServiceImpl implements WeatherService{

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Override
    public String getForecast(String stateCode) {
        switch (stateCode){
            case "FL":
                return "Hot";
            case "MA":
                return "Cold";
        }

        return "???";
    }
}

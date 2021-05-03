package org.kpn.ch11.service;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.kpn.ch11.entities.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("scheduledCarService")
@Repository
@Transactional
public class ScheduledCarService extends CarServiceImpl{

    private static final Logger logger = LoggerFactory.getLogger(ScheduledCarService.class);

    @Scheduled(fixedDelay = 10000)
    @Override
    public void updateCarAgeJob() {
        List<Car> cars = findAll();

        DateTime currentDate = DateTime.now();
        logger.info("Car age update job started");

        cars.forEach(car -> {
            int age = Years.yearsBetween(car.getManufactureDate(), currentDate).getYears();
            car.setAge(age);
            save(car);
            logger.info("Car age update --> {}", car);
        });

        logger.info("Car age update job completed successfully");
    }
}

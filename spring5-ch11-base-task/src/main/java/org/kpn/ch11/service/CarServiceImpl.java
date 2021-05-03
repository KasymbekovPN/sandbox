package org.kpn.ch11.service;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.kpn.ch11.entities.Car;
import org.kpn.ch11.repos.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService{

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private boolean done;

    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

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
        done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
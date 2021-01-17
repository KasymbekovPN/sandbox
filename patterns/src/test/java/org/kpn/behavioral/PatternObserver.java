package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;

import java.util.LinkedList;
import java.util.List;

@DisplayName("Observer")
public class PatternObserver {
//    public class WeatherStation {
//        public static void main(String[] args) {
//            WeatherData weatherData = new WeatherData();
//
//            Observer currentDisplay = new CurrentConditionsDisplay ();
//
//            weatherData.registerObserver(currentDisplay);
//
//            weatherData.setMeasurements(29f, 65f, 745);
//            weatherData.setMeasurements(39f, 70f, 760);
//            weatherData.setMeasurements(42f, 72f, 763);
//        }
//    }
//

    private interface Observer{
        void update(float temperature, float humidity, int pressure);
    }

    private static class CurrentConditionsDisplay implements Observer{
        private float temperature;
        private float humidity;
        private int pressure;

        @Override
        public void update(float temperature, float humidity, int pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            display();
        }

        public void display(){
            System.out.printf("T: %s; H: %s; P: %s%n", temperature, humidity, pressure);
        }
    }

    private interface Observable{
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObserver();
    }

    private static class WeatherData implements Observable{

        private List<Observer> observers = new LinkedList<>();
        private float temperature;
        private float humidity;
        private int pressure;

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObserver() {
            for (Observer observer : observers) {
                observer.update(temperature, humidity, pressure);
            }
        }

        public void setMeasurements(float temperature, float humidity, int pressure){
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;

            notifyObserver();
        }
    }
}

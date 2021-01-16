package org.kpn;

import org.junit.jupiter.api.DisplayName;

/*
    Class must contain related logic
 */
@DisplayName("GRASP: high cohesion")
public class n5_HighCohesion {

    public static class WrongData{

        private int temperature;
        private int time;

        public WrongData(int temperature, int time) {
            this.temperature = temperature;
            this.time = time;
        }

        public int calculateTimeDifference(int time){
            return this.time = time;
        }

        public int calculateTempDifferent(int temperature){
            return this.temperature - temperature;
        }
    }

    public static class TempData{
        private int temp;

        public TempData(int temp) {
            this.temp = temp;
        }

        public int calcDiff(int temp){
            return this.temp - temp;
        }
    }

    public static class TimeData{
        private int time;

        public TimeData(int time) {
            this.time = time;
        }

        public int calcDiff(int time){
            return this.time - time;
        }
    }

    public static class Data{
        private TempData tempData;
        private TimeData timeData;

        public Data(int time, int temp) {
            this.tempData = new TempData(temp);
            this.timeData = new TimeData(time);
        }
    }
}

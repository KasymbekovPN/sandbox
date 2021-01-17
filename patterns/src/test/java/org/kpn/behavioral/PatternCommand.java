package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("Command")
public class PatternCommand {

    private interface Command{
        void execute();
    }

    // The invoker
    private static class Switch{
        private final Map<String, Command> commands = new HashMap<>();

        public void register(String commandName, Command command){
            commands.put(commandName, command);
        }

        public void execute(String commandName){
            Command command = commands.get(commandName);
            if (command == null){
                throw new IllegalStateException("no command registered for " + commandName);
            }

            command.execute();
        }
    }

    // The receiver
    private static class Light {

        public void turnOn(){
            System.out.println("The light is ON");
        }

        public void turnOff(){
            System.out.println("The light is OFF");
        }
    }

    private static class SwitchOnCmd implements Command {

        private final Light light;

        public SwitchOnCmd(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    private static class SwitchOffCmd implements Command{

        private final Light light;

        public SwitchOffCmd(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

    @Test
    void test(){

        Light light = new Light();

        Command switchOnCmd = new SwitchOnCmd(light);
        Command switchOffCmd = new SwitchOffCmd(light);

        Switch switcher = new Switch();
        switcher.register("on", switchOnCmd);
        switcher.register("off", switchOffCmd);

        switcher.execute("on");
        switcher.execute("off");
    }

    @Test
    void test8(){
        Light light = new Light();

        Command switchOnCmd = light::turnOn;
        Command switchOffCmd = light::turnOff;

        Switch switcher = new Switch();
        switcher.register("on", switchOnCmd);
        switcher.register("off", switchOffCmd);

        switcher.execute("on");
        switcher.execute("off");
    }
}

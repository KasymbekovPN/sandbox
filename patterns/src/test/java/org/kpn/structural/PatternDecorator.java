package org.kpn.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Decorator")
public class PatternDecorator {

    private interface Component{
        void doOperation();
    }

    private static class MainComponent implements Component{
        @Override
        public void doOperation() {
            System.out.println("World!");
        }
    }

    private static abstract class Decorator implements Component{
        protected Component component;

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void doOperation() {
            component.doOperation();
        }

        public void newOperation(){
            System.out.println("Do nothing");
        }
    }

    private static class DecoratorSpace extends Decorator{
        public DecoratorSpace(Component component) {
            super(component);
        }

        @Override
        public void doOperation() {
            System.out.print(" ");
            super.doOperation();
        }

        @Override
        public void newOperation() {
            System.out.println("New space operations");
        }
    }

    private static class DecoratorComma extends Decorator{
        public DecoratorComma(Component component) {
            super(component);
        }

        @Override
        public void doOperation() {
            System.out.print(",");
            super.doOperation();
        }

        @Override
        public void newOperation() {
            System.out.println("New comma operation");
        }
    }

    private static class DecoratorHello extends Decorator{
        public DecoratorHello(Component component) {
            super(component);
        }

        @Override
        public void doOperation() {
            System.out.print("Hello");
            super.doOperation();
        }

        @Override
        public void newOperation() {
            System.out.println("New hello operation");
        }
    }

    @Test
    void test(){
        DecoratorHello d = new DecoratorHello(new DecoratorComma(new DecoratorSpace(new MainComponent())));
        d.doOperation();
        d.newOperation();
    }
}

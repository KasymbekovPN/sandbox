package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("State")
public class PatternState {

    private interface State{
        String getName();
        void freeze(StateContext context);
        void heat(StateContext context);
    }

    private static class SolidState implements State{

        private static final String NAME = "solid";

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public void freeze(StateContext context) {
            System.out.println("Nothing happens");
        }

        @Override
        public void heat(StateContext context) {
            context.setState(new LiquidState());
        }
    }

    private static class LiquidState implements State{

        private static final String NAME = "liquid";

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public void freeze(StateContext context) {
            context.setState(new SolidState());
        }

        @Override
        public void heat(StateContext context) {
            context.setState(new GaseousState());
        }
    }

    private static class GaseousState implements State{

        private final static String NAME = "gaseous";

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public void freeze(StateContext context) {
            context.setState(new LiquidState());
        }

        @Override
        public void heat(StateContext context) {
            System.out.println("Nothing happens");
        }
    }

    private interface StateContext{
        void freeze();
        void heat();
        void setState(State state);
        State getState();
    }

    private static class StateContextImpl implements StateContext{

        private State state = new SolidState();

        @Override
        public void freeze() {
            System.out.println("Freezing " + state.getName() + " substance... ");
            state.freeze(this);
        }

        @Override
        public void heat() {
            System.out.println("Heating " + state.getName() + " substance... ");
            state.heat(this);
        }

        @Override
        public void setState(State state) {
            System.out.println("Changing state to " + state.getName() + "...");
            this.state = state;
        }

        @Override
        public State getState() {
            return state;
        }
    }

    @Test
    void test(){
        StateContextImpl context = new StateContextImpl();
        context.heat();
        context.heat();
        context.heat();
        context.freeze();
        context.freeze();
        context.freeze();
    }
}

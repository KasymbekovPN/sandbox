package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Strategy")
public class PatternStrategy {

    private interface Strategy{
        int execute(int a, int b);
    }

    private static class AddStrategy implements Strategy{
        @Override
        public int execute(int a, int b) {
            System.out.println("Called AddStrategy's execute()");
            return a + b;
        }
    }

    private static class SubStrategy implements Strategy{
        @Override
        public int execute(int a, int b) {
            System.out.println("Called SubStrategy's execute()");
            return a - b;
        }
    }

    private static class MulStrategy implements Strategy{
        @Override
        public int execute(int a, int b) {
            System.out.println("Called MulStrategy's execute()");
            return a * b;
        }
    }

    private static class Context{

        private Strategy strategy;

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        public int execute(int a, int b){
            return strategy.execute(a, b);
        }
    }

    @Test
    void test(){

        Context context = new Context();

        context.setStrategy(new AddStrategy());
        System.out.println(context.execute(3, 4));

        context.setStrategy(new SubStrategy());
        System.out.println(context.execute(3, 4));

        context.setStrategy(new MulStrategy());
        System.out.println(context.execute(3, 4));
    }
}

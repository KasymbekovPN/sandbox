package org.kpn.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Proxy")
public class PatternProxy {

    private interface Math{
        double add(double x, double y);
        double sub(double x, double y);
    }

    private static class MathImpl implements Math{

        @Override
        public double add(double x, double y) {
            System.out.println("called add");
            return x + y;
        }

        @Override
        public double sub(double x, double y) {
            System.out.println("called sub");
            return x - y;
        }
    }

    private static class MathProxy implements Math{

        private Math math;

        @Override
        public double add(double x, double y) {
            lazyInitMath();
            return math.add(x, y);
        }

        @Override
        public double sub(double x, double y) {
            lazyInitMath();
            return math.sub(x, y);
        }

        private void lazyInitMath(){
            if (math == null){
                System.out.println("math init");
                math = new MathImpl();
            }
        }
    }

    @Test
    public void test(){
        MathProxy mathProxy = new MathProxy();
        System.out.println("4 + 2 = " + mathProxy.add(4, 2));
        System.out.println("4 - 2 = " + mathProxy.sub(4, 2));
    }
}

package org.kpn.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Abstract factory")
public class PatternAbstractFactory {

    private static class Client {
        private final ProductA productA;
        private final ProductB productB;

        Client(Factory factory){
            productA = factory.createProductA();
            productB = factory.createProductB();
        }

        void execute() {
            productB.interact(productA);
        }
    }

    private interface ProductA {
        void interact(ProductB b);
    }

    private interface ProductB {
        void interact(ProductA a);
    }

    private static class ProductA1 implements ProductA {
        @Override
        public void interact(ProductB b) {
            System.out.println(this.getClass().getName() + " interacts with " + b.getClass().getName());
        }
    }

    private static class ProductA2 implements ProductA {
        @Override
        public void interact(ProductB b) {
            System.out.println(this.getClass().getName() + " interacts with " + b.getClass().getName());
        }
    }

    private static class ProductB1 implements ProductB {
        @Override
        public void interact(ProductA a) {
            System.out.println(this.getClass().getName() + " interacts with " + a.getClass().getName());
        }
    }

    private static class ProductB2 implements ProductB {
        @Override
        public void interact(ProductA a) {
            System.out.println(this.getClass().getName() + " interacts with " + a.getClass().getName());
        }
    }

    private interface Factory {
        ProductA createProductA();
        ProductB createProductB();
    }

    private static class Factory1 implements Factory {
        @Override
        public ProductA createProductA() {
            return new ProductA1();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB1();
        }
    }

    private static class Factory2 implements Factory {
        @Override
        public ProductA createProductA() {
            return new ProductA2();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB2();
        }
    }

    @Test
    void test(){
        Client client1 = new Client(new Factory1());
        client1.execute();

        Client client2 = new Client(new Factory2());
        client2.execute();
    }
}

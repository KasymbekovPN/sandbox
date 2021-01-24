package org.kpn.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Factory method")
public class PatternFactoryMethod {

    private interface Product{}
    private static class ProductA implements Product{}
    private static class ProductB implements Product{}

    private interface Creator{
        Product factoryMethod();
    }

    private static class CreatorA implements Creator{
        @Override
        public Product factoryMethod() {
            return new ProductA();
        }
    }

    private static class CreatorB implements Creator{
        @Override
        public Product factoryMethod() {
            return new ProductB();
        }
    }

    @Test
    void test(){
        Creator[] creators = {
                new CreatorA(),
                new CreatorB()
        };

        for (Creator creator : creators) {
            Product product = creator.factoryMethod();
            System.out.println("Created " + product.getClass().getName());
        }
    }
}

package org.kpn.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Builder")
public class PatternBuilder {

    private static class SomeClass{

        private final int x;
        private final int y;
        private final int z;

        private SomeClass(Builder builder) {
            x = builder.x;
            y = builder.y;
            z = builder.z;
        }

        @Override
        public String toString() {
            return "SomeClass{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }

        private static class Builder{
            private final int x;

            private int y = 0;
            private int z = 0;

            public Builder(int x) {
                this.x = x;
            }

            public Builder setY(int y) {
                this.y = y;
                return this;
            }

            public Builder setZ(int z) {
                this.z = z;
                return this;
            }

            public SomeClass build(){
                return new SomeClass(this);
            }
        }
    }

    @Test
    void test(){
        SomeClass someClass = new SomeClass.Builder(123)
                .setY(456)
                .setZ(789)
                .build();
        System.out.println(someClass);
    }
}

package org.kpn.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Prototype")
public class PatternPrototype {

    private static class Cookie implements Cloneable{

        protected int weight;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Cookie clone = (Cookie) super.clone();

            /* some code */

            return clone;
        }
    }

    private static class CoconutCookie extends Cookie{}

    private static class CookieMachine{

        private final Cookie cookie;

        public CookieMachine(Cookie cookie) {
            this.cookie = cookie;
        }

        public Cookie make() throws CloneNotSupportedException {
            return (Cookie) this.cookie.clone();
        }
    }

    @Test
    void test() throws CloneNotSupportedException {
        CoconutCookie prototype = new CoconutCookie();
        CookieMachine machine = new CookieMachine(prototype);
        System.out.println("prototype: " + prototype);
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " : " + machine.make());
        }
    }
}

package org.kpn.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("Flyweight")
public class PatternFlyweight {

    private static abstract class Char{
        protected final char symbol;
        protected final int wight;
        protected final int height;

        public Char(char symbol, int wight, int height) {
            this.symbol = symbol;
            this.wight = wight;
            this.height = height;
        }

        public abstract void print();
    }

    private static class CharA extends Char{

        public CharA() {
            super('A', 10, 20);
        }

        @Override
        public void print() {
            System.out.println("[ " + symbol + " | " + wight + " | " + height + " ]");
        }
    }

    private static class CharB extends Char{

        public CharB() {
            super('B', 20, 30);
        }

        @Override
        public void print() {
            System.out.println("[ " + symbol + " | " + wight + " | " + height + " ]");
        }
    }

    private static class FlyweightFactory {

        private final Map<Integer, Char> characters = new HashMap<>();

        public Char getChar(int code){
            Char ch = characters.get(code);
            if (ch == null){
                switch (code){
                    case 0x41:
                        ch = new CharA();
                        break;
                    case 0x42:
                        ch = new CharB();
                        break;
                }
                characters.put(code, ch);
            }

            return ch;
        }
    }

    @Test
    void test(){
        int [] codes = {0x41, 0x42, 0x41, 0x42};
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        for (int code : codes) {
            Char ch = flyweightFactory.getChar(code);
            System.out.println(code + " : " + ch);
            ch.print();
        }
    }
}

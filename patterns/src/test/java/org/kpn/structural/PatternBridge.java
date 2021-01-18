package org.kpn.structural;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bridge")
public class PatternBridge {

    private interface Drawer{
        void drawCircle(int x, int y, int radius);
    }


    private static class SmallCircleDrawer implements Drawer{

        private static final double radiusMultiplier = 0.25;

        @Override
        public void drawCircle(int x, int y, int radius) {
            System.out.println("Small circle center = (" + x + "," + y + "), radius = " + radius * radiusMultiplier);
        }
    }

    private static class LargeCircleDrawer implements Drawer{

        private static final double radiusMultiplier = 10.0;

        @Override
        public void drawCircle(int x, int y, int radius) {
            System.out.println("Large circle center = (" + x + "," + y + "), radius = " + radius * radiusMultiplier);
        }
    }

    private static abstract class Shape{

        protected Drawer drawer;

        protected Shape(Drawer drawer) {
            this.drawer = drawer;
        }

        public abstract void draw();
        public abstract void enlargeRadius(int multiplier);
    }

    @Getter
    @Setter
    private static class Circle extends Shape{
        private int x;
        private int y;
        private int radius;

        public Circle(Drawer drawer, int x, int y, int radius) {
            super(drawer);
            setX(x);
            setY(y);
            setRadius(radius);
        }

        @Override
        public void draw() {
            drawer.drawCircle(x, y, radius);
        }

        @Override
        public void enlargeRadius(int multiplier) {
            radius *= multiplier;
        }
    }

    @Test
    void test(){

        Shape[] shapes = {
                new Circle(new LargeCircleDrawer(), 5,10,10),
                new Circle(new SmallCircleDrawer(), 20,30,100)
        };

        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}

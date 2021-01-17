package org.kpn.behavioral;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Visitor")
public class PatternVisitor {

    @Getter
    @Setter
    private static abstract class Point{

        private double metric = -1;

        public abstract void accept(Visitor visitor);
    }

    @Getter
    @AllArgsConstructor
    private static class Point2d extends Point{

        private final double x;
        private final double y;

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Point3d extends Point{

        private final double x;
        private final double y;
        private final double z;

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    private interface Visitor{
        void visit(Point2d p);
        void visit(Point3d p);
    }

    private static class Euclid implements Visitor{
        @Override
        public void visit(Point2d p) {
            p.setMetric(Math.sqrt(
                    p.getX()*p.getX() + p.getY()*p.getY()
            ));
        }

        @Override
        public void visit(Point3d p) {
            p.setMetric(Math.sqrt(
                    p.getX()*p.getX() + p.getY()*p.getY() + p.getZ()*p.getZ()
            ));
        }
    }

    private static class Chebyshev implements Visitor{
        @Override
        public void visit(Point2d p) {
            double ax = Math.abs(p.getX());
            double ay = Math.abs(p.getY());
            p.setMetric(Math.max(ax, ay));
        }

        @Override
        public void visit(Point3d p) {
            double ax = Math.abs(p.getX());
            double ay = Math.abs(p.getY());
            double az = Math.abs(p.getZ());
            p.setMetric(
                    Math.max(Math.max(ax, ay), az)
            );
        }
    }

    @Test
    void test(){
        Point2d point2d = new Point2d(1, 2);
        Chebyshev v = new Chebyshev();
        point2d.accept(v);

        System.out.println(point2d.getMetric());
    }
}

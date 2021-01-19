package org.kpn.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Composite")
public class PatternComposite {

    // component
    private interface Graphic{
        void print();
        default Graphic add(Graphic graphic){return this;}
        default Graphic remove(Graphic graphic){return this;}
    }

    // Composite
    private static class CompositeGraphic implements Graphic{

        private final List<Graphic> children = new ArrayList<>();

        @Override
        public void print() {
            for (Graphic child : children) {
                child.print();
            }
        }

        @Override
        public Graphic add(Graphic graphic) {
            children.add(graphic);
            return this;
        }

        @Override
        public Graphic remove(Graphic graphic) {
            children.remove(graphic);
            return this;
        }
    }

    // leaf
    private static class Leaf implements Graphic {

        @Override
        public void print() {
            System.out.println("Leaf");
        }
    }

    @Test
    void test(){
        Graphic g = new CompositeGraphic()
                .add(
                        new CompositeGraphic()
                                .add(new Leaf())
                                .add(new Leaf())
                )
                .add(
                        new CompositeGraphic()
                                .add(new Leaf())
                );

        g.print();
    }
}

package org.kpn.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Adapter, composition")
public class PatternAdapterComposition {

    private interface Chief{
        Object makeBreakfast();
        Object makeLunch();
        Object makeDinner();
    }

    static private class Plumber{
        public Object getScrewNut(){return "ScrewNut";}
        public Object getPipe(){return "Pipe";}
        public Object getGasket(){return "Gasket";}
    }

    private static class ChiefAdapter implements Chief{

        private final Plumber plumber = new Plumber();

        @Override
        public Object makeBreakfast() {
            return plumber.getScrewNut();
        }

        @Override
        public Object makeLunch() {
            return plumber.getPipe();
        }

        @Override
        public Object makeDinner() {
            return plumber.getGasket();
        }
    }

    @Test
    void test(){

        ChiefAdapter chiefAdapter = new ChiefAdapter();
        eat(chiefAdapter.makeBreakfast());
        eat(chiefAdapter.makeLunch());
        eat(chiefAdapter.makeDinner());
    }

    private static void eat(Object dish){
        System.out.println(dish);
    }
}

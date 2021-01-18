package org.kpn.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Adapter, inheritance")
public class PatternAdapterInheritance {

    // target
    private interface Chief{
        Object makeBreakfast();
        Object makeLunch();
        Object makeDinner();
    }

    // adaptee
    static private class Plumber{
        public Object getScrewNut(){return "ScrewNut";}
        public Object getPipe(){return "Pipe";}
        public Object getGasket(){return "Gasket";}
    }

    // adapter
    private static class ChiefAdapter extends Plumber implements Chief{

        @Override
        public Object makeBreakfast() {
            return getGasket();
        }

        public Object makeLunch() {
            return getPipe();
        }

        @Override
        public Object makeDinner() {
            return getScrewNut();
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

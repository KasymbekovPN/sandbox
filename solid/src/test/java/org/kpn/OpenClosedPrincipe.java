package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.kpn.util.Order;

@DisplayName("Open closed principe")
public class OpenClosedPrincipe {

    /*
        open for extending,
        closed for changing
     */
    public static class ExtendedOrderProcessor extends SimpleResponsibilityPrincipe.OrderProcessor{

        @Override
        public void process(Order order) {
            beforeProcessing(order);
            super.process(order);
            afterProcessing(order);
        }

        private void beforeProcessing(Order order){
            /* some processing */
        }

        private void afterProcessing(Order order){
            /* some processing */
        }
    }
}

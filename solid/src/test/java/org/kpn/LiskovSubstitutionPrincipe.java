package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.kpn.util.Item;
import org.kpn.util.Order;

@DisplayName("Liskov's substitution principe")
public class LiskovSubstitutionPrincipe {

    public static class OrderStockValidator{

        public boolean validate(Order order){
            return order.getItems().stream().allMatch(Item::isInStock);
        }
    }

    public static class OrderStockAndPackedValidator extends OrderStockValidator{

        @Override
        public boolean validate(Order order) {
            return super.validate(order) && order.getItems().stream().allMatch(Item::isPacked);
        }
    }

    /*
        !!! parent class can't throw exception !!!
     */
    public static class WrongStockAndPackValidator extends OrderStockValidator{

        @Override
        public boolean validate(Order order) {

            for (Item item : order.getItems()) {
                if (!item.isInStock() || !item.isPacked()){
                    throw new IllegalStateException(String.format("The order %s isn't valid!", order.getId()));
                }
            }

            return true;
        }
    }
}

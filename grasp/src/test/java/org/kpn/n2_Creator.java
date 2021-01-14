package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.kpn.utils.Good;
import org.kpn.utils.creator.Order_Cr;
import org.kpn.utils.infornationExpert.OrderItem_IE;
import org.kpn.utils.infornationExpert.Order_IE;

import java.util.ArrayList;

@DisplayName("GRASP: creator")
public class n2_Creator {

    public static class WrongClient{

        public void doSth(){
            int amount = 10;
            Good good = new Good("name", 2.0);
            OrderItem_IE orderItem_ie = new OrderItem_IE(good, amount);
            ArrayList<OrderItem_IE> orderItem_ies = new ArrayList<>();
            orderItem_ies.add(orderItem_ie);

            Order_IE order = new Order_IE(orderItem_ies, "address");
        }
    }

    public static class Client{

        public void doSth(){

            int amount = 10;
            String name = "name";
            double price = 1.23;

            Order_Cr order = new Order_Cr("address");
            order.addOrderItem(amount, name, price);
        }
    }
}

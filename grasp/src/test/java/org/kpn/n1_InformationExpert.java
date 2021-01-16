package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.kpn.utils.infornationExpert.Order_IE;
import org.kpn.utils.infornationExpert.OrderItem_WrongIE;
import org.kpn.utils.infornationExpert.Order_WrongIE;

import java.util.ArrayList;
import java.util.List;

/*
    Information must being processed where it storage
 */
@DisplayName("GRASP: information expert")
public class n1_InformationExpert {

    public static class WrongClient{

        public void doSth() {

        }

        private float getOrderPrice(Order_WrongIE order){
            List<OrderItem_WrongIE> orderItems = order.getOrderItems();
            float result = 0;

            for (OrderItem_WrongIE orderItem : orderItems) {
                result += orderItem.getAmount() * orderItem.getGood().getPrice();
            }

            return result;
        }
    }

    public static class Client{

        public void doSth(){
            Order_IE orderIE = new Order_IE(new ArrayList<>(), "");
            orderIE.getPrice();
        }
    }
}

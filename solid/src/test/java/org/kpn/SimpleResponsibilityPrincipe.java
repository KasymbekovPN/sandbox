package org.kpn;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Simple responsibility principe")
public class SimpleResponsibilityPrincipe {

    private static class Order{

        private boolean valid = true;
        private String name = "someName";
        private String email = "some@email.com";

        public boolean isValid(){
            return valid;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    /*
        several responsibilities
    */
    private static class WrongOrderProcessor{

        public void process(Order order){
            if (order.isValid() && save(order)){
                sendEmail(order);
            }
        }

        private boolean save(Order order){

            /* save to DB */

            return true;
        }

        private void sendEmail(Order order){
            String name = order.getName();
            String email = order.getEmail();

            /* send email*/
        }
    }

    /* one responsibility - work with DB*/
    public static class OrderRepository{

        public boolean save(Order order){

            /* save to DB */

            return true;
        }
    }

    /* one responsibility - mailing */
    public static class OrderEmailSender{

        public void sendEmail(Order order){
            String name = order.getName();
            String email = order.getEmail();

            /* send email*/
        }
    }

    /* one responsibility - processing */
    public static class OrderProcessor{

        public void process(Order order){

            OrderRepository orderRepository = new OrderRepository();
            OrderEmailSender orderEmailSender = new OrderEmailSender();

            if (order.isValid() && orderRepository.save(order)){
                orderEmailSender.sendEmail(order);
            }
        }
    }
}

package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.kpn.util.Order;

/*
    Dependencies within system are built on basis of abstractions (interfaces)
 */
@DisplayName("Dependency inversion principe")
public class DependencyInversionPrincipe {


    public interface OrderRepository{
        boolean save(Order order);
    }

    public interface OrderEmailSender{
        void sendEmail(Order order);
    }

    public interface OrderProcess{
        void process(Order order);
    }

    public static class OrderRepositoryImpl implements OrderRepository{

        public boolean save(Order order){

            /* save to DB */

            return true;
        }
    }

    public static class OrderEmailSenderImpl implements OrderEmailSender{

        public void sendEmail(Order order){
            String name = order.getName();
            String email = order.getEmail();

            /* send email*/
        }
    }


    public static class OrderProcessorImpl{

        private final OrderRepository repository;
        private final OrderEmailSender emailSender;

        public OrderProcessorImpl(OrderRepository repository, OrderEmailSender emailSender) {
            this.repository = repository;
            this.emailSender = emailSender;
        }

        public void process(Order order){

            if (order.isValid() && repository.save(order)){
                emailSender.sendEmail(order);
            }
        }
    }

}

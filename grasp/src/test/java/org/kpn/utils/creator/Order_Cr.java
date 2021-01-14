package org.kpn.utils.creator;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class Order_Cr {

    private List<OrderItem_Cr> orderItems = new ArrayList<>();
    private String destinationAddress;

    public Order_Cr(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public double getPrice(){
        return orderItems.stream().collect(Collectors.summarizingDouble(OrderItem_Cr::getPrice)).getSum();
    }

    public void addOrderItem(int amount, String name, double price){
        orderItems.add(new OrderItem_Cr(amount, name, price));
    }
}

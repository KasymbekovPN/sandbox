package org.kpn.utils.infornationExpert;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Order_IE {
    private final List<OrderItem_IE> orderItemIES;
    private final String destinationAddress;

    public double getPrice(){
        return orderItemIES.stream().collect(Collectors.summarizingDouble(OrderItem_IE::getPrice)).getSum();
    }
}

package org.kpn.utils.infornationExpert;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Order_WrongIE {
    private final List<OrderItem_WrongIE> orderItems;
    private final String destinationAddress;
}

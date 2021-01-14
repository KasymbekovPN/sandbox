package org.kpn.utils.infornationExpert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kpn.utils.Good;

@Getter
@AllArgsConstructor
public class OrderItem_IE {
    private final Good good;
    private final int amount;

    public double getPrice(){
        return amount * good.getPrice();
    }
}

package org.kpn.utils.creator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kpn.utils.Good;

@Setter
@Getter
@AllArgsConstructor
public class OrderItem_Cr {

    private Good good;
    private int amount;

    public OrderItem_Cr(int amount, String name, double price) {
        this.amount = amount;
        this.good = new Good(name, price);
    }

    public double getPrice(){
        return amount * good.getPrice();
    }
}

package org.kpn.utils.infornationExpert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kpn.utils.Good;

@Getter
@AllArgsConstructor
public class OrderItem_WrongIE {
    private final Good good;
    private final int amount;
}

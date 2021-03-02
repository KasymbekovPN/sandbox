package org.kpn.ch4.highschool;

import org.kpn.ch4.Food;
import org.kpn.ch4.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {

    @Override
    public List<Food> provideLunchSet() {
        return new ArrayList<>(){{
            add(new Food("Coke"));
            add(new Food("Hamburger"));
            add(new Food("French fries"));
        }};
    }
}

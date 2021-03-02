package org.kpn.ch4.kindergarten;

import org.kpn.ch4.Food;
import org.kpn.ch4.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {

    @Override
    public List<Food> provideLunchSet() {
        return new ArrayList<>(){{
            add(new Food("Milk"));
            add(new Food("Biscuits"));
        }};
    }
}

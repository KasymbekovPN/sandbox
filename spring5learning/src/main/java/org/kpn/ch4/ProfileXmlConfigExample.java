package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:ch4/*-config.xml");
        ctx.refresh();

        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
        for (Food food : foodProviderService.provideLunchSet()) {
            System.out.println("Food: " + food.getName());
        }

        ctx.close();
    }
}

//-Dspring.profiles.active="highschool"

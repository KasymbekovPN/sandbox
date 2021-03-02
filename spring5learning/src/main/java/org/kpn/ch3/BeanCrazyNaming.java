package org.kpn.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class BeanCrazyNaming {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch3/app-context-03.xml");

        ctx.getBeansOfType(String.class).forEach((key, value) -> {
            System.out.printf("id: %s\n\taliases: %s\n", key, Arrays.toString(ctx.getAliases(key)));
        });

        ctx.close();
    }
}

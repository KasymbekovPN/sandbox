package org.kpn.ch3.annotated;

import org.kpn.ch3.helloWorld.provider.MessageProvider;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ConstrInject {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("helloWorld/app-context-constructor-inject.xml");
        ctx.refresh();
        final MessageProvider provider = ctx.getBean("provider", MessageProvider.class);

        System.out.println(provider.getMessage());
    }
}

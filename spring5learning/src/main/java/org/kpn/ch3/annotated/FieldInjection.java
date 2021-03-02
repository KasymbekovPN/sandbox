package org.kpn.ch3.annotated;

import org.kpn.ch3.annotated.singer.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FieldInjection {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("helloWorld/app-context-fieldInjection.xml");
        ctx.refresh();

        Singer singer = ctx.getBean("singer", Singer.class);
        singer.sing();
        ctx.close();
    }
}

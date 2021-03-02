package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NamedSingerDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-NamedSinger.xml");

        NamedSinger johnMayer = ctx.getBean("johnMayer", NamedSinger.class);
        johnMayer.sing();

        ctx.close();
    }
}

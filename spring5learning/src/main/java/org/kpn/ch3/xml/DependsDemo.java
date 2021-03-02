package org.kpn.ch3.xml;

import org.kpn.ch3.xml.singer.AwareSinger;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DependsDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("xml/app-context-01.xml");

        AwareSinger johnMayer = ctx.getBean("johnMayer", AwareSinger.class);
        johnMayer.sing();

        ctx.close();
    }
}

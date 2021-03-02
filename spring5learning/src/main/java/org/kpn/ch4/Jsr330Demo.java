package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Jsr330Demo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-annotation-jsr-330.xml");

        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();

        ctx.close();
    }
}

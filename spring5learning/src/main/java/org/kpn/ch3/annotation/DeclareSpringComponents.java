package org.kpn.ch3.annotation;

import org.kpn.ch3.helloWorld.render.MessageRender;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("helloWorld/app-context-annotation.xml");
        ctx.refresh();
        MessageRender renderer = ctx.getBean("renderer", MessageRender.class);
        renderer.render();

        ctx.close();
    }
}

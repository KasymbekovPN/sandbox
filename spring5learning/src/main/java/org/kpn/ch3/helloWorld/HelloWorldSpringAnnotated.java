package org.kpn.ch3.helloWorld;

import org.kpn.ch3.helloWorld.render.MessageRender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        final MessageRender render = ctx.getBean("render", MessageRender.class);

        render.render();
    }
}

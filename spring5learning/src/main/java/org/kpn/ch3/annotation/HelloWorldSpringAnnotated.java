package org.kpn.ch3.annotation;

import org.kpn.ch3.annotation.config.HWConfig2;
import org.kpn.ch3.helloWorld.render.MessageRender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HWConfig1.class);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HWConfig2.class);
        MessageRender render = ctx.getBean("render", MessageRender.class);

        render.render();
    }
}

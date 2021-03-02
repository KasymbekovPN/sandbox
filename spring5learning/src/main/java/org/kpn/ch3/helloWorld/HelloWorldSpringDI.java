package org.kpn.ch3.helloWorld;

import org.kpn.ch3.helloWorld.render.MessageRender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpringDI {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("helloWorld/app-context.xml");
        MessageRender mr = ctx.getBean("render", MessageRender.class);
        mr.render();
    }
}

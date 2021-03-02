package org.kpn.ch4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void publish(String message){
        ctx.publishEvent(new MessageEvent(this, message));
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ch4/app-context-xml-messageEvent.xml");

        Publisher publisher = ctx.getBean("publisher", Publisher.class);
        publisher.publish("message 1");
        publisher.publish("message 2");

        ctx.close();
    }
}

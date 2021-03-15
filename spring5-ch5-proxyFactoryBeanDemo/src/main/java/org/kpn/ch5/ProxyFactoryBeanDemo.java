package org.kpn.ch5;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryBeanDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context-xml.xml");
        ctx.refresh();

        Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        System.out.println("Documentarist one >>> ");
        documentaristOne.execute();

        System.out.println("Documentarist two >> ");
        documentaristTwo.execute();

        ctx.close();
    }
}

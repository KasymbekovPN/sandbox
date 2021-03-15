package org.kpn.ch5;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class IntroductionConfigDemo {
    public static void main(String[] args) {

//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        ctx.load("app-context-xml.xml");
//        ctx.refresh();

        Contract bean = ctx.getBean("bean", Contract.class);
        IsModified mod = (IsModified) bean;

        System.out.println("Is Contract? :" + (bean instanceof Contract));
        System.out.println("Is IsModified? :" + (bean instanceof IsModified));
        System.out.println("Has been modified? :" + mod.isModified());

        bean.setName("John Mayer");
        System.out.println("Has been modified? :" + mod.isModified());

        bean.setName("Eric Clapton");
        System.out.println("Has been modified? :" + mod.isModified());
    }
}

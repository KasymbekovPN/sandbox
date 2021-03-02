package org.kpn.ch3.annotated;

import org.kpn.ch3.annotated.singer.NonSingletonSinger;
import org.springframework.context.support.GenericXmlApplicationContext;

public class NonSingletonDemo {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("xml/app-context-xml-noSingleton.xml");

        NonSingletonSinger s1 = ctx.getBean("nonSingleton", NonSingletonSinger.class);
        NonSingletonSinger s2 = ctx.getBean("nonSingleton", NonSingletonSinger.class);
        check(s1, s2);

        NonSingletonSinger s3 = ctx.getBean("singleton", NonSingletonSinger.class);
        NonSingletonSinger s4 = ctx.getBean("singleton", NonSingletonSinger.class);
        check(s3, s4);

        ctx.close();
    }

    private static void check(NonSingletonSinger s1, NonSingletonSinger s2){

        System.out.println("----");
        System.out.println("Identity equal? : " + (s1 == s2));
        System.out.println("Value equal? : " + s1.equals(s2));
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
    }
}

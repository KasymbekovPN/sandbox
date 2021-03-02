package org.kpn.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Target {

    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public Target() {
        System.out.println("Call new Target()");
    }

    public Target(Foo foo){
        System.out.println("Call new Target(Foo)");
    }

    public Target(Foo foo, Bar bar){
        System.out.println("Call new Target(Foo, Bar)");
    }

    public void setFooOne(Foo fooOne) {
        System.out.println("Property fooOne set");
        this.fooOne = fooOne;
    }

    public void setFooTwo(Foo fooTwo) {
        System.out.println("Property fooTwo set");
        this.fooTwo = fooTwo;
    }

    public void setBar(Bar bar) {
        System.out.println("Property bar set");
        this.bar = bar;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("xml/app-context-03.xml");

        Target t = null;
        System.out.println("Using by name\n");
        t = (Target) ctx.getBean("targetByName");

        System.out.println("\nUsing byType\n");
        t = (Target) ctx.getBean("targetByType");

        System.out.println("\nUsing constructor\n");
        t = (Target) ctx.getBean("targetConstructor");

        ctx.close();
    }
}

package org.kpn.ch3.xml;

import org.kpn.ch3.helloWorld.oracle.Oracle;
import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectRef {

    private Oracle oracle;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("xml/app-context-xml.xml");
        ctx.refresh();

        InjectRef injectRef = (InjectRef) ctx.getBean("injectRef");
        System.out.println(injectRef);

        ctx.close();
    }

    public void setOracle(Oracle oracle) {
        this.oracle = oracle;
    }

    @Override
    public String toString() {
        return oracle.defineMeaningOfLife();
    }
}

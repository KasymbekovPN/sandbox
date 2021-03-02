package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestFactoryDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-messageDigestDemo1.xml");

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello world!!!");

        ctx.close();
    }
}

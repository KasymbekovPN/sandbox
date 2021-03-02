package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-messageDigestDemo.xml");

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello world!!!");

        ctx.close();
    }
}

package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AccessingFactoryBeans {
    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-messageDigestDemo.xml");

        MessageDigestFactoryBean factory = (MessageDigestFactoryBean) ctx.getBean("&shaDigest");

        byte[] out = factory.getObject().digest("Hello world!!!".getBytes());
        System.out.println(out);

        ctx.close();;
    }
}

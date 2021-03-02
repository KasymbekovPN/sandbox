package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PlaceHolderDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-app-property.xml");

        AppProperty appProperty = ctx.getBean("appProperty", AppProperty.class);
        System.out.println(appProperty);

        ctx.close();
    }
}

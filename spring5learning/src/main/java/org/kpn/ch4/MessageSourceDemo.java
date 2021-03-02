package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class MessageSourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-messageSource.xml");

        Locale english = new Locale("en", "GB");
        Locale russian = new Locale("ru", "RU");

        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, russian));

        System.out.println(ctx.getMessage("nameMsg", new Object[]{"name", "lastName"}, english));
        System.out.println(ctx.getMessage("nameMsg", new Object[]{"name", "lastName"}, russian));

        ctx.close();
    }
}

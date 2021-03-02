package org.kpn.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

public class BeanNamingTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch3/app-context-01.xml");
        Map<String, String> beansOfType = ctx.getBeansOfType(String.class);
        beansOfType.forEach((key, value) -> System.out.printf("%s\n", key));
        ctx.close();
    }
}

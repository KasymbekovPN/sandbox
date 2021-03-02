package org.kpn.ch3.annotated;

import org.kpn.ch3.annotated.demoBean.DemoBean;
import org.kpn.ch3.annotated.singer.ProtoSinger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

public class LooKupConfigDemo {

    @Configuration
    @ComponentScan(basePackages = "org.kpn.ch3.annotated")
    public static class LookupConfig{}

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(LookupConfig.class);

        DemoBean a = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean s = ctx.getBean("standardLookupBean", DemoBean.class);

        displayInfo("abstractLookupBean", a);
        displayInfo("standardLookupBean", s);

        ctx.close();
    }

    public static void displayInfo(String beanName, DemoBean bean){
        ProtoSinger singer1 = bean.getMySinger();
        ProtoSinger singer2 = bean.getMySinger();
        System.out.println("" + beanName + ": singer instances the same? " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for (int i = 0; i < 1_000_000; i++) {
            ProtoSinger mySinger = bean.getMySinger();
            mySinger.sing();
        }
        stopWatch.stop();

        System.out.println("1_000_000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}

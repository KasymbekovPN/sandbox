package org.kpn.ch3.xml;

import org.kpn.ch3.xml.demoBean.DemoBean;
import org.kpn.ch3.xml.singer.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class LookupDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("xml/app-context-xml.xml");
        ctx.refresh();

        DemoBean abstractBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean standardBean = ctx.getBean("standardLookupBean", DemoBean.class);

        displayInfo("abstractLookupBean", abstractBean);
        displayInfo("standardLookupBean", standardBean);

        ctx.close();
    }

    public static void displayInfo(String beanName, DemoBean bean){
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();
        System.out.println("" + beanName + ": singer instances the same? " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for (int i = 0; i < 1_000_000; i++) {
            Singer mySinger = bean.getMySinger();
            mySinger.sing();
        }
        stopWatch.stop();

        System.out.println("1_000_000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}

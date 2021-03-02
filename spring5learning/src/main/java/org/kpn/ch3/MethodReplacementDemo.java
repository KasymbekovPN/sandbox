package org.kpn.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch3/reimpl-app-context-xml.xml");

        ReplacementTarget replacementTarget = ctx.getBean("replacementTarget", ReplacementTarget.class);
        ReplacementTarget standardTarget = ctx.getBean("standardTarget", ReplacementTarget.class);

        displayInfo(replacementTarget);
        displayInfo(standardTarget);

        ctx.close();
    }

    private static void displayInfo(ReplacementTarget target){
        System.out.println(target.formatMessage("Thanks for playing, try again!"));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfTest");
        for (int i = 0; i < 1_000_000; i++) {
            String out = target.formatMessage("No filter in my head");
//            System.out.println(out);
        }
        stopWatch.stop();

        System.out.println("1_000_000 invocations tool: " + stopWatch.getTotalTimeMillis() + " ms");
    }
}

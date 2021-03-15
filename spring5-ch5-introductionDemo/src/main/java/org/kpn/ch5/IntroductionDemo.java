package org.kpn.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class IntroductionDemo {
    public static void main(String[] args) {
        Contract target = new Contract();
        target.setName("John Mayer");

        IsModifiedAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setOptimize(true);

        Contract proxy = (Contract) pf.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is Contract? :" + (proxy instanceof Contract));
        System.out.println("Is IsModified? :" + (proxy instanceof IsModified));
        System.out.println("Has been modified? :" + proxyInterface.isModified());

        proxy.setName("John Mayer");
        System.out.println("Has been modified? :" + proxyInterface.isModified());

        proxy.setName("Eric Clapton");
        System.out.println("Has been modified? :" + proxyInterface.isModified());
    }
}

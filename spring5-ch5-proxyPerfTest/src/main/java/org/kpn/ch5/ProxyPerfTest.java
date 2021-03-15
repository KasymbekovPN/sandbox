package org.kpn.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyPerfTest {

    private static final int SIZE = 500_000;

    public static void main(String[] args) {
        DefaultSimpleBean target = new DefaultSimpleBean();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvise());
        runCglibTests(advisor, target);
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean target){
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        SimpleBean bean = (SimpleBean) pf.getProxy();
        System.out.println("Running CGLIB (Standard) Tests");
        test(bean);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean target){
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setFrozen(true);

        SimpleBean bean = (SimpleBean) pf.getProxy();
        System.out.println("Running CGLIB (Frozen) Tests");
        test(bean);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean target){
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setInterfaces(SimpleBean.class);

        SimpleBean bean = (SimpleBean) pf.getProxy();
        System.out.println("Running JDK Tests");
        test(bean);
    }

    private static void test(SimpleBean bean){
        long before = 0;
        long after = 0;

        System.out.println("Testing advised Method");
        before = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            bean.advised();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing unadvised method");
        before = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            bean.unadvised();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing equals() method");
        before = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            bean.equals(bean);
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println("Testing hashCode() method");
        before = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            bean.hashCode();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        Advised advised = (Advised) bean;
        System.out.println("Testing Advised.getTargetProxyClass() method");
        before = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();
        System.out.println("Took " + (after - before) + " ms");

        System.out.println(">>>\n");
    }
}

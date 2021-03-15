package org.kpn.ch5;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ControlFlowDemo {
    public static void main(String[] args) {
        ControlFlowDemo controlFlowDemo = new ControlFlowDemo();
        controlFlowDemo.run();
    }

    public void run(){
        TestBean testBean = new TestBean();
        ControlFlowPointcut pc = new ControlFlowPointcut(ControlFlowDemo.class, "test");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(testBean);
        pf.addAdvisor(advisor);
        TestBean proxy = (TestBean) pf.getProxy();

        System.out.println("Trying normal invoke");
        proxy.foo();

        System.out.println("\nTrying under ControlFlowDemo::test");
        test(proxy);
    }

    public void test(TestBean bean){
        bean.foo();
    }
}

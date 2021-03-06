package org.kpn.ch5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AgentDecorator implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.print("James ");
        Object ret = methodInvocation.proceed();
        System.out.println("!");
        return ret;
    }
}

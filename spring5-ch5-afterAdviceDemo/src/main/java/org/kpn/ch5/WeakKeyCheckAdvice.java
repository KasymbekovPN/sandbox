package org.kpn.ch5;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class WeakKeyCheckAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        if ((o1 instanceof KeyGenerator) && ("getKey".equals(method.getName()))){
            long key = (Long) o;
            if (key == KeyGenerator.WEAK_KEY){
                throw new SecurityException("Key generator generate weak key. Try again.");
            }
        }
    }
}

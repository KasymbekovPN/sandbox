package org.kpn.ch5;

import org.aspectj.lang.JoinPoint;

public class SimpleAdvice1 {
    public void simpleBeforeAdvice(JoinPoint joinPoint){
        System.out.println("Executing : " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName());
    }
}

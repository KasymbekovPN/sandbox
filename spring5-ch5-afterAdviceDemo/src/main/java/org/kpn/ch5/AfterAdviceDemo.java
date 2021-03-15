package org.kpn.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceDemo {

    public static void main(String[] args) {
        KeyGenerator keyGenerator = getKeyGenerator();
        for (int i = 0; i < 10; i++) {
            try{
                long key = keyGenerator.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static KeyGenerator getKeyGenerator(){
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(new KeyGenerator());
        pf.addAdvice(new WeakKeyCheckAdvice());

        return (KeyGenerator) pf.getProxy();
    }
}

package org.kpn.ch5;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {

    public static void main(String[] args) {
        ErrorBean errorBean = getErrorBean();
        try{
            errorBean.errorPhoneMethod();
        } catch (Exception ex){
        }

        try{
            errorBean.otherErrorPhoneMethod();
        } catch (Exception ex){
        }
    }

    private static ErrorBean getErrorBean(){
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(new ErrorBean());
        pf.addAdvice(new SimpleThrowsAdvice());

        return (ErrorBean) pf.getProxy();
    }

    public void afterThrowing(Exception ex) throws Throwable{
        System.out.println("***");
        System.out.println("Generic exception capture");
        System.out.println("Caught: " + ex.getClass().getName());
        System.out.println("***\n");
    }

    public void afterThrowing(Method method,
                              Object[] args,
                              Object target,
                              IllegalArgumentException ex) throws Throwable{

        System.out.println("***");
        System.out.println("Illegal argument exception capture");
        System.out.println("Caught: " + ex.getClass().getName());
        System.out.println("Method: " + method.getName());
        System.out.println("***\n");
    }
}

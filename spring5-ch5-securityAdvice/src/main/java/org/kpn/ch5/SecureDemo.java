package org.kpn.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class SecureDemo {

    public static void main(String[] args) {

        SecureBean secureBean = getSecureBean();
        SecureManager secureManager = new SecureManager();

        secureManager.login("John", "pwd");
        secureBean.writeSecureMessage();
        secureManager.logout();

        try{
            secureManager.login("invalid user", "pwd");
            secureBean.writeSecureMessage();
        } catch (SecurityException ex){
            System.out.println("Exception caught: " + ex.getMessage());
        } finally {
            secureManager.logout();
        }

        try{
            secureBean.writeSecureMessage();
        } catch (SecurityException ex){
            System.out.println("Exception caught: " + ex.getMessage());
        }
    }

    private static SecureBean getSecureBean(){
        SecureBean target = new SecureBean();
        SecurityAdvice securityAdvice = new SecurityAdvice();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(securityAdvice);

        return (SecureBean) pf.getProxy();
    }
}

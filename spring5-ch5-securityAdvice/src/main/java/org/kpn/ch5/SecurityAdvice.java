package org.kpn.ch5;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    private final SecureManager secureManager;

    public SecurityAdvice() {
        this.secureManager = new SecureManager();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

        UserInfo user = secureManager.getLoggedOnUser();
        if (user ==  null){
            System.out.println("No user authenticated");
            throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
        } else if ("John".equals(user.getUserName())){
            System.out.println("Logged in user is John - OK");
        } else {
            System.out.println("Logged in user is " + user.getUserName() + "NOT GOOD");
            throw new SecurityException("User " + user.getUserName() + "isn't allowed access to method " + method.getName());
        }
    }
}

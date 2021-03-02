package org.kpn.ch3;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.util.Arrays;

public class FormatMessageReplacer implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        if (isFormatMessageMethod(method)){
            String msg = (String) args[0];
            return "<h2>" + msg + "</h2>";
        } else {
            throw new IllegalAccessException("Unable to reimplement method " + method.getName());
        }
    }

    private boolean isFormatMessageMethod(Method method){
        if (method.getParameterTypes().length != 1){
            return false;
        }

        if (!("formatMessage".equals(method.getName()))){
            return false;
        }

        if (!method.getReturnType().equals(String.class)){
            return false;
        }

        if (!method.getParameterTypes()[0].equals(String.class)){
            return false;
        }

        return true;
    }
}

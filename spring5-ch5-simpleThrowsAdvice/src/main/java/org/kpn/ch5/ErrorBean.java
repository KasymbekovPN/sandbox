package org.kpn.ch5;

public class ErrorBean {

    public void errorPhoneMethod() throws Exception{
        throw new Exception("Generic exception");
    }

    public void otherErrorPhoneMethod() throws IllegalArgumentException{
        throw new IllegalArgumentException("Illegal Argument");
    }
}

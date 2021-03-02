package org.kpn.ch3.helloWorld.provider;

public class HelloWorldMessageProvider  implements MessageProvider{
    @Override
    public String getMessage() {
        return "Hello world!!!";
    }
}

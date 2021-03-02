package org.kpn.ch3.annotation.provider;

import org.kpn.ch3.helloWorld.provider.MessageProvider;
import org.springframework.stereotype.Component;

@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {

    @Override
    public String getMessage() {
        return "Hello world!!!";
    }
}

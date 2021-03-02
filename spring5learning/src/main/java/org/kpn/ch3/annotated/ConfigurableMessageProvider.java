package org.kpn.ch3.annotated;

import org.kpn.ch3.helloWorld.provider.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;

//@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {

    private String message;

    @Autowired
    public ConfigurableMessageProvider(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

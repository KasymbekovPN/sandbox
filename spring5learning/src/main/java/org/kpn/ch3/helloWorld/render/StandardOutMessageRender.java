package org.kpn.ch3.helloWorld.render;

import org.kpn.ch3.helloWorld.provider.MessageProvider;

public class StandardOutMessageRender implements MessageRender{

    private MessageProvider provider;

    @Override
    public void render() {
        if (provider == null){
            throw new RuntimeException("You must set the property of class: "
                    + StandardOutMessageRender.class.getName());
        }
        System.out.println(provider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.provider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return provider;
    }
}

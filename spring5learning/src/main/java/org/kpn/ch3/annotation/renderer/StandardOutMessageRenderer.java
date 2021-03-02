package org.kpn.ch3.annotation.renderer;

import org.kpn.ch3.helloWorld.provider.MessageProvider;
import org.kpn.ch3.helloWorld.render.MessageRender;
import org.kpn.ch3.helloWorld.render.StandardOutMessageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("renderer")
public class StandardOutMessageRenderer implements MessageRender {

    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null){
            throw new RuntimeException("You must set the property of class: "
                    + StandardOutMessageRender.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    @Autowired
    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}

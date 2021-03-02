package org.kpn.ch4;

import javax.inject.*;

@Named("messageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer{

    @Inject
    @Named("messageProvider")
    private MessageProvider messageProvider = null;

    @Override
    public void render() {
        if (messageProvider == null){
            throw new RuntimeException("You must set the property messageProvider of " + StandardOutMessageRenderer.class.getName());
        }

        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}

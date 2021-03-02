package org.kpn.ch3.helloWorld;

import org.kpn.ch3.helloWorld.provider.MessageProvider;
import org.kpn.ch3.helloWorld.render.MessageRender;

import java.util.Properties;

public class MessageSupportFactory {

    private static MessageSupportFactory instance;

    private Properties properties;
    private MessageRender messageRender;
    private MessageProvider messageProvider;

    private MessageSupportFactory() {
        properties = new Properties();

        try{
            properties.load(this.getClass().getResourceAsStream("/msf.properties"));

            String renderClass = properties.getProperty("render.class");
            String providerClass = properties.getProperty("provider.class");

            messageRender = (MessageRender) Class.forName(renderClass).getConstructor().newInstance();
            messageProvider = (MessageProvider) Class.forName(providerClass).getConstructor().newInstance();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRender getMessageRender() {
        return messageRender;
    }

    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}

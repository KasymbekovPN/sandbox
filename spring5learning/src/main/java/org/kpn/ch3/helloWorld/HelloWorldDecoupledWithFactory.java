package org.kpn.ch3.helloWorld;

import org.kpn.ch3.helloWorld.provider.MessageProvider;
import org.kpn.ch3.helloWorld.render.MessageRender;

public class HelloWorldDecoupledWithFactory {
    public static void main(String[] args) {
        final MessageRender mr = MessageSupportFactory.getInstance().getMessageRender();
        final MessageProvider mp = MessageSupportFactory.getInstance().getMessageProvider();

        mr.setMessageProvider(mp);
        mr.render();
    }
}

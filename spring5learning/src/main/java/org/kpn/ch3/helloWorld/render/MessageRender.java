package org.kpn.ch3.helloWorld.render;

import org.kpn.ch3.helloWorld.provider.MessageProvider;

public interface MessageRender {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}

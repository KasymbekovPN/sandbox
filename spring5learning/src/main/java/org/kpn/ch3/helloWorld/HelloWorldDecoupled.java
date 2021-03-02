package org.kpn.ch3.helloWorld;

import org.kpn.ch3.helloWorld.provider.HelloWorldMessageProvider;
import org.kpn.ch3.helloWorld.render.StandardOutMessageRender;

public class HelloWorldDecoupled {
    public static void main(String[] args) {
        final HelloWorldMessageProvider mp = new HelloWorldMessageProvider();
        final StandardOutMessageRender mr = new StandardOutMessageRender();
        mr.setMessageProvider(mp);
        mr.render();
    }
}

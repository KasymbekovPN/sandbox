package org.kpn.ch3.annotation.config;

import org.kpn.ch3.helloWorld.provider.HelloWorldMessageProvider;
import org.kpn.ch3.helloWorld.provider.MessageProvider;
import org.kpn.ch3.helloWorld.render.MessageRender;
import org.kpn.ch3.helloWorld.render.StandardOutMessageRender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public MessageProvider provider(){
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRender render(){
        StandardOutMessageRender r = new StandardOutMessageRender();
        r.setMessageProvider(provider());

        return r;
    }
}

package org.kpn.ch12.config;

import org.kpn.ch12.services.SingerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class RmiClientConfig {

    @Bean
    public SingerService singerService(){
        HttpInvokerProxyFactoryBean factory = new HttpInvokerProxyFactoryBean();
        factory.setServiceInterface(SingerService.class);
        factory.setServiceUrl("http://localhost:8080/invoker/httpInvoker/singerService");
        factory.afterPropertiesSet();

        return (SingerService) factory.getObject();
    }
}

package org.kpn.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Contract guitarist(){
        Contract guitarist = new Contract();
        guitarist.setName("John Mayer");
        return guitarist;
    }

    @Bean
    public Advisor advisor(){
        return new IsModifiedAdvisor();
    }

    @Bean
    public ProxyFactoryBean bean() {
        ProxyFactoryBean bean = new ProxyFactoryBean();
        bean.setTarget(guitarist());
        bean.addAdvisor(advisor());
        bean.setProxyTargetClass(true);

        return bean;
    }
}

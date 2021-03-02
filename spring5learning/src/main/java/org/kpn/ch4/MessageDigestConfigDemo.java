package org.kpn.ch4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class MessageDigestConfigDemo {

    @Configuration
    static class MessageDigesterConfig{

        @Bean
        public MessageDigestFactoryBean shaDigest(){
            MessageDigestFactoryBean factory = new MessageDigestFactoryBean();
            factory.setAlgorithmName("SHA1");

            return factory;
        }

        @Bean
        public MessageDigestFactoryBean defaultDigest(){
            return new MessageDigestFactoryBean();
        }

        @Bean
        public MessageDigester digester() throws Exception {
            MessageDigester messageDigester = new MessageDigester();
            messageDigester.setDigest1(shaDigest().getObject());
            messageDigester.setDigest2(defaultDigest().getObject());

            return messageDigester;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MessageDigesterConfig.class);
        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);

        digester.digest("Hello world!!!");

        ctx.close();;
    }
}

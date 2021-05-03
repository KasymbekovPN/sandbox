package org.kpn.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
        jmsTemplate.setDeliveryDelay(5_000L);
        for (int i = 0; i < 10; i++) {
            String testMessage = String.format("Test message %s", i);
            logger.info(">>> Sending: {}", testMessage);
            jmsTemplate.convertAndSend("prospring5", testMessage);
        }

        System.in.read();
        ctx.close();
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> connectionFactory(ConnectionFactory connectionFactory,
                                                                                          DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);

        return factory;
    }

    @JmsListener(destination = "prospring5", containerFactory = "connectionFactory")
    public void onMessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try{
            logger.info(">>> Received: {}", textMessage.getText());
        } catch (JMSException ex){
            logger.error("JMS error: {}", ex.getMessage());
        }
    }
}

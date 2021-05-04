package org.kpn.ch18;

import org.kpn.ch18.web.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.kpn.ch18")
public class ServerConfig {

    @Bean
    public Server server(){
        return new Server();
    }
}

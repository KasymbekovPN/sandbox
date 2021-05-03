package org.kpn.ch11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({DataServiceConfig.class})
@ImportResource("spring/task-namespace-app-context.xml")
public class AppConfig {}

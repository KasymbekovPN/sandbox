package org.kpn.ch3.annotation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "helloWorld/app-context.xml")
@Configuration
public class HWConfig2 {
}

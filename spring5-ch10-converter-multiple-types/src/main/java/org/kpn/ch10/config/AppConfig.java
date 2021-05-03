package org.kpn.ch10.config;

import org.kpn.ch10.Singer;
import org.kpn.ch10.SingerToAnotherSingerConverter;
import org.kpn.ch10.StringToDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;

@Configuration
@ComponentScan(basePackages = "org.kpn.ch10")
public class AppConfig {

    @Bean
    public Singer john() throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setPersonalSite(new URL("http://johnmayer.com/"));
        singer.setBirthDate(converter().convert("1977-10-16"));

        return singer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService(){
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        HashSet<Converter<?, ?>> convs = new HashSet<>() {{
            add(converter());
            add(singerConverter());
        }};
        factory.setConverters(convs);
        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    StringToDateTimeConverter converter(){
        return new StringToDateTimeConverter();
    }

    @Bean
    SingerToAnotherSingerConverter singerConverter(){
        return new SingerToAnotherSingerConverter();
    }
}

package org.kpn.ch12.init;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.kpn.ch12")
public class WebConfig implements WebMvcConfigurer {
//package com.apress.prospring5.ch12.init;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;


//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
//import org.springframework.oxm.castor.CastorMarshaller;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//    /**
//     * Created by iuliana.cosmina on 6/5/17.
//     */

//    public class WebConfig implements WebMvcConfigurer {
//

    @Autowired
    ApplicationContext ctx;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());

        return converter;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        return objectMapper;
    }
//        @Override
//        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//            converters.add(mappingJackson2HttpMessageConverter());
//            converters.add(singerMessageConverter());
//        }
//
//        @Bean MarshallingHttpMessageConverter singerMessageConverter() {
//            MarshallingHttpMessageConverter mc = new MarshallingHttpMessageConverter();
//            mc.setMarshaller(castorMarshaller());
//            mc.setUnmarshaller(castorMarshaller());
//            List<MediaType> mediaTypes = new ArrayList<>();
//            MediaType mt = new MediaType("application", "xml");
//            mediaTypes.add(mt);
//            mc.setSupportedMediaTypes(mediaTypes);
//            return mc;
//        }
//

//    @Bean
//    CastorMarshaller
//        @Bean CastorMarshaller castorMarshaller() {
//            CastorMarshaller castorMarshaller = new CastorMarshaller();
//            castorMarshaller.setMappingLocation(ctx.getResource("classpath:spring/oxm-mapping.xml"));
//            return castorMarshaller;
//        }
//    }
}

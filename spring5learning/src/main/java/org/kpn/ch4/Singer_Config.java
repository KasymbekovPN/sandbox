package org.kpn.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Singer_Config {

    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        System.out.println("call setName");
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("Call setAge");
        this.age = age;
    }

    private void init(){
        System.out.println("Initializing bean");

        if (name == null){
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE){
            throw new IllegalArgumentException("You must set the age property of any beans of type " + Singer_Config.class);
        }
    }

    @Override
    public String toString() {
        return "Singer_InitMethod0{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);

        ctx.close();
    }

    public static Singer_Config getBean(String beanName, ApplicationContext ctx) {
        try{
            Singer_Config bean = (Singer_Config) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex){
            System.out.println("An error occured in bean configuration: " + ex.getMessage());
        }

        return null;
    }

    @Configuration
    public static class Config{

        @Bean(initMethod = "init")
        @Lazy
        public Singer_Config singerOne(){
            Singer_Config singer = new Singer_Config();
            singer.setName("John Mayer");
            singer.setAge(39);
            return singer;
        }

        @Bean(initMethod = "init")
        @Lazy
        public Singer_Config singerTwo(){
            Singer_Config singer = new Singer_Config();
            singer.setAge(72);
            return singer;
        }

        @Bean(initMethod = "init")
        @Lazy
        public Singer_Config singerThree(){
            Singer_Config singer = new Singer_Config();
            singer.setName("John Butler");
            return singer;
        }
    }
}

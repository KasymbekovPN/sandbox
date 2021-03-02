package org.kpn.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Singer_InitMethod0 {

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
            throw new IllegalArgumentException("You must set the age property of any beans of type " + Singer_InitMethod0.class);
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
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-init-method0.xml");

        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);

        ctx.close();
    }

    public static Singer_InitMethod0 getBean(String beanName, ApplicationContext ctx) {
        try{
            Singer_InitMethod0 bean = (Singer_InitMethod0) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex){
            System.out.println("An error occured in bean configuration: " + ex.getMessage());
        }

        return null;
    }
}

package org.kpn.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Singer_WithInterface implements InitializingBean {

    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        System.out.println("Call setName");
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("Call setAge");
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");

        if (name == null){
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE){
            throw new IllegalArgumentException("You must set the age property of any beans of type " + Singer_WithInterface.class);
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
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-init-interface0.xml");

        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);

        ctx.close();
    }

    public static Singer_WithInterface getBean(String beanName, ApplicationContext ctx) {
        try{
            Singer_WithInterface bean = (Singer_WithInterface) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex){
            System.out.println("An error occured in bean configuration: " + ex.getMessage());
        }

        return null;
    }
}

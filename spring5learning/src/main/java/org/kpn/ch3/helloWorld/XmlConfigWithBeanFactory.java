package org.kpn.ch3.helloWorld;

import org.kpn.ch3.helloWorld.oracle.Oracle;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlConfigWithBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader rdr = new XmlBeanDefinitionReader(factory);
        rdr.loadBeanDefinitions("helloWorld/xml-bean-factory-config.xml");
        Oracle oracle = (Oracle) factory.getBean("oracle");

        System.out.println(oracle.defineMeaningOfLife());
    }
}

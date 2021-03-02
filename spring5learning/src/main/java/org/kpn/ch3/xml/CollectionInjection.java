package org.kpn.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionInjection {

    private Map<String, Object> map;
    private Properties props;
    private Set<Object> set;
    private List<Object> list;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("xml/app-context-xml.xml");
        ctx.refresh();

        CollectionInjection injectCollection = (CollectionInjection) ctx.getBean("injectCollection");
        injectCollection.displayInfo();

        ctx.close();
    }

    private void displayInfo(){
        System.out.println("Map content:");
        map.forEach((key, value) -> System.out.printf(
                "Key: %s - Value: %s%n", key, value
        ));

        System.out.println("Properties contents:");
        props.forEach((key, value) -> System.out.printf(
                "Key: %s - Value: %s%n", key, value
        ));

        System.out.println("Set contents");
        set.forEach(o -> System.out.printf("Value: %s\n", o));

        System.out.println("\nList contents");
        list.forEach(o -> System.out.printf("Value: %s\n", o));
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public void setSet(Set<Object> set) {
        this.set = set;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}

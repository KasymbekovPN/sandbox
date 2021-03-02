package org.kpn.ch3.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service("injectCollection")
public class ResCollectionInjection {

//    @Resource(name = "map")
    //<
    @Autowired
    @Qualifier("map")
    private Map<String, Object> map;

    @Resource(name = "props")
    private Properties props;

    @Resource(name = "set")
    private Set<Object> set;

    @Resource(name = "list")
    private List<Object> list;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("xml/app-context-annotation.xml");
        ctx.refresh();

        ResCollectionInjection injectCollection = (ResCollectionInjection) ctx.getBean("injectCollection");
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
}

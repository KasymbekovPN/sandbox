package org.kpn.ArrayList;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ArrayListDemo {

    private static final Map<String, Runnable> DEMOS = new HashMap<>(){{
        put("demoAdd", ArrayListDemo::demoAdd);
        put("demoGet", ArrayListDemo::demoGet);
        put("demoIndexOf", ArrayListDemo::demoIndexOf);
        put("demoContains", ArrayListDemo::demoContains);
        put("demoAddWithIndex", ArrayListDemo::demoAddWithIndex);
        put("demoSet", ArrayListDemo::demoSet);
    }};

    public static void main(String[] args) {
//        String key = "demoAdd";
//        String key = "demoGet";
//        String key = "demoIndexOf";
//        String key = "demoContains";
//        String key = "demoAddWithIndex";
        String key = "demoSet";
        if (DEMOS.containsKey(key)){
            DEMOS.get(key).run();
        } else {
            log.error("Invalid key : {}", key);
        }
    }

    private static void demoAdd(){
        ArrayList<Integer> list = new ArrayList<>();
        Integer[] integers = {1, 2, 3, 1};
        for (Integer integer : integers) {
            log.info("add {} - result {}", integer, list.add(integer));
        }

        log.info("{}", list);
    }

    private static void demoGet(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 1, 2, 3));
        for (int i = 0; i < list.size(); i++) {
            log.info("get({}) - result {}", i, list.get(i));
        }
    }

    private static void demoIndexOf(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("value0", "value1"));
        ArrayList<String> findValues = new ArrayList<>(list);
        findValues.add("value2");

        for (String findValue : findValues) {
            log.info("indexOf({}) - result {}", findValue, list.indexOf(findValue));
        }
    }

    private static void demoContains(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("value0", "value1"));
        ArrayList<String> findValues = new ArrayList<>(list);
        findValues.add("value2");

        for (String findValue : findValues) {
            log.info("contains({}) - result {}", findValue, list.contains(findValue));
        }
    }

    private static void demoAddWithIndex(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("value0", "value1", "value2"));
        list.add(1, "added");
        log.info("{}", list);
    }

    private static void demoSet(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("value0", "value1", "value2"));
        list.set(1, "added");
        log.info("{}", list);
    }
}

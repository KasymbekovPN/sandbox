package org.kpn.HashMap;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HashMapDemo {

    private static Map<String, Runnable> DEMOS = new HashMap<>(){{
        put("demoPut", HashMapDemo::demoPut);
        put("demoGet", HashMapDemo::demoGet);
        put("demoRemove", HashMapDemo::demoRemove);
        put("demoContains", HashMapDemo::demoContains);
        put("demoKeySet", HashMapDemo::demoKeySet);
        put("demoValues", HashMapDemo::demoValues);
        put("demoSize", HashMapDemo::demoSize);
        put("demoPutAll", HashMapDemo::demoPutAll);
        put("demoEntrySet", HashMapDemo::demoEntrySet);
    }};

    public static void main(String[] args) {
//        String key = "demoPut";
//        String key = "demoGet";
//        String key = "demoRemove";
//        String key = "demoContains";
//        String key = "demoKeySet";
//        String key = "demoValues";
//        String key = "demoSize";
//        String key = "demoPutAll";
        String key = "demoEntrySet";
        if (DEMOS.containsKey(key)){
            DEMOS.get(key).run();
        } else {
            log.error("Invalid key : {}", key);
        }
    }

    private static void demoPut(){
        Integer[] integers = {0, 1, 2, 0, 1, 2};
        HashMap<Integer, String> map = new HashMap<>();
        int count = 0;
        for (Integer i : integers) {
            String value = String.format("value%s", count++);
            log.info("put {} >< {} - result : {}", i, value, map.put(i, value));
        }
        log.info("map : {}", map);
    }

    private static void demoGet(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(i, String.format("value%s", i));
        }

        for (int i = 0; i < 4; i++) {
            log.info("key {}, value {}", i, map.get(i));
        }
    }

    private static void demoRemove() {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(i, String.format("value%s", i));
        }

        for (int i = 0; i < 3; i++) {
            log.info("key {}, value {}", i, map.get(i));
        }
        log.info("removing by key {} - result {}", 1, map.remove(1));
        for (int i = 0; i < 3; i++) {
            log.info("key {}, value {}", i, map.get(i));
        }
    }

    private static void demoContains(){
        HashMap<Integer, String> map = new HashMap<>();
        Integer[] keys = {0, 1, 2, 3};
        String[] values = {"value0", "value1", "value2", "value3"};

        for (int i = 0; i < 3; i++) {
            map.put(i, String.format("value%s", i));
        }

        for (Integer key : keys) {
            log.info("contains key {} - result {}", key, map.containsKey(key));
        }

        for (String value : values) {
            log.info("contains value {} - result {}", value, map.containsValue(value));
        }
    }

    private static void demoKeySet(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(i, String.format("value%s", i));
        }
        log.info("result of setKey {}", map.keySet());
    }

    private static void demoValues(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(i, String.format("value%s", i));
        }
        log.info("result of values {}", map.values());
    }

    private static void demoSize(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(i, String.format("value%s", i));
        }

        log.info("size {}", map.size());
        log.info("isEmpty {}", map.isEmpty());
        map.clear();
        log.info("size {}", map.size());
        log.info("isEmpty {}", map.isEmpty());
    }

    private static void demoPutAll(){
        HashMap<Integer, String> firstMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            firstMap.put(i, String.format("first%s", i));
        }

        HashMap<Integer, String> secondMap = new HashMap<>();
        for (int i = 2; i < 5; i++) {
            secondMap.put(i, String.format("second%s", i));
        }

        firstMap.putAll(secondMap);
        log.info("{}", firstMap);
    }

    private static void demoEntrySet(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(i, String.format("value%s", i));
        }
        log.info("Entry set {}", map.entrySet());
    }
}

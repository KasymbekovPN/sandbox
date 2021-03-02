package org.kpn.HashSet;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class HashSetDemo {

    private static final Map<String, Runnable> DEMOS = new HashMap<>(){{
        put("demoAdd", HashSetDemo::demoAdd);
        put("demoClear", HashSetDemo::demoClear);
        put("demoContains", HashSetDemo::demoContains);
        put("demoRemove", HashSetDemo::demoRemove);
        put("demoIterator", HashSetDemo::demoIterator);
        put("demoIsEmpty", HashSetDemo::demoIsEmpty);
        put("demoClone", HashSetDemo::demoClone);
    }};

    private static final HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) {
//        String key = "demoAdd";
//        String key = "demoClear";
//        String key = "demoContains";
//        String key = "demoRemove";
//        String key = "demoIterator";
//        String key = "demoIsEmpty";
        String key = "demoClone";
        if (DEMOS.containsKey(key)){
            DEMOS.get(key).run();
        } else {
            log.error("Invalid key {}", key);
        }
    }

    private static void fillSet(){
        for (int i = 0; i < 4; i++) {
            set.add(i);
        }
    }

    private static void demoAdd(){
        fillSet();
        for (int i = 3; i < 6; i++) {
            log.info("{}", set);
            set.add(i);
        }
        log.info("{}", set);
    }

    private static void demoClear(){
        fillSet();
        log.info("{}",set);
        set.clear();
        log.info("{}",set);
    }

    private static void demoContains(){
        fillSet();
        log.info("{}", set);
        for (int i = 2; i < 6; i++){
            log.info("contains({}) - {}", i, set.contains(i));
        }
    }

    private static void demoRemove(){
        fillSet();
        log.info("{}", set);
        log.info("remove({}) - {}", 1, set.remove(1));
        log.info("remove({}) - {}", 1, set.remove(1));
        log.info("remove({}) - {}", 3, set.remove(3));
        log.info("{}", set);
    }

    private static void demoIterator(){
        fillSet();
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()){
            log.info("{}", it.next());
        }
    }

    private static void demoIsEmpty(){
        fillSet();
        log.info("isEmpty() - {}", set.isEmpty());
        set.clear();
        log.info("isEmpty() - {}", set.isEmpty());
    }

    private static void demoClone(){
        fillSet();
        Set<String> otherSet = (Set<String>) set.clone();
        log.info("set {}", set);
        log.info("otherSet {}", otherSet);
        set.clear();
        log.info("set {}", set);
        log.info("otherSet {}", otherSet);
    }
}

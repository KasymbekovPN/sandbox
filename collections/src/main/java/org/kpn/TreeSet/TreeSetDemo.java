package org.kpn.TreeSet;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class TreeSetDemo {

    private static final Map<String, Runnable> DEMOS = new HashMap<>(){{
        put("demoCreation", TreeSetDemo::demoCreation);
        put("demoAdd", TreeSetDemo::demoAdd);
        put("demoContains", TreeSetDemo::demoContains);
        put("demoRemove", TreeSetDemo::demoRemove);
        put("demoClear", TreeSetDemo::demoClear);
        put("demoSize", TreeSetDemo::demoSize);
        put("demoIsEmpty", TreeSetDemo::demoIsEmpty);
        put("demoIterator", TreeSetDemo::demoIterator);
        put("demoIteratorException", TreeSetDemo::demoIteratorException);
        put("demoFirstLastAndPoll", TreeSetDemo::demoFirstLastAndPoll);
        put("demoSubHeadTail", TreeSetDemo::demoSubHeadTail);
    }};

    public static void main(String[] args) {
//        String key = "demoCreation";
//        String key = "demoAdd";
//        String key = "demoContains";
//        String key = "demoRemove";
//        String key = "demoClear";
//        String key = "demoSize";
//        String key = "demoIsEmpty";
//        String key = "demoIterator";
//        String key = "demoIteratorException";
//        String key = "demoFirstLastAndPoll";
        String key = "demoSubHeadTail";
        if (DEMOS.containsKey(key)){
            DEMOS.get(key).run();
        } else {
            log.info("Invalid key {}", key);
        }
    }

    private static final TreeSet<String> set = new TreeSet<>();

    private static void fillSet(int max){
        for (int i = 0; i <= max; i++) {
            set.add(String.format("value%s", i));
        }
    }

    private static void demoCreation(){
        TreeSet<String> set0 = new TreeSet<>();
        log.info("{} - {}", set0.getClass(), set0);

        TreeSet<String> set1 = new TreeSet<>(Comparator.comparing(String::length));
        log.info("{} - {}", set1.getClass(), set1);

        // Синхронизирующая обёртка
        Set<String> set2 = Collections.synchronizedSet(new TreeSet<String>());
        log.info("{} - {}", set2.getClass(), set2);
    }

    private static void demoAdd(){
        fillSet(2);
        log.info("{}", set);
        log.info("call add({}) - {}", "value2", set.add("value2"));
        log.info("call add({}) - {}", "value3", set.add("value3"));
    }

    private static void demoContains(){
        fillSet(2);
        log.info("{}", set);
        log.info("call contains({}) - {}", "value2", set.contains("value2"));
        log.info("call contains({}) - {}", "value3", set.contains("value3"));
    }

    private static void demoRemove(){
        fillSet(2);
        log.info("{}", set);
        log.info("call remove({}) - {}", "value2", set.remove("value2"));
        log.info("call remove({}) - {}", "value3", set.remove("value3"));
        log.info("{}", set);
    }

    private static void demoClear(){
        fillSet(2);
        log.info("{}", set);
        set.clear();
        log.info("{}", set);
    }

    private static void demoSize(){
        fillSet(2);
        log.info("{} - {}", set.size(), set);
        set.add("value3");
        log.info("{} - {}", set.size(), set);
    }

    private static void demoIsEmpty(){
        fillSet(2);
        log.info("{} - {}", set.isEmpty(), set);
        set.clear();
        log.info("{} - {}", set.isEmpty(), set);
    }

    // There is used fail-safe iterator
    private static void demoIterator(){
        fillSet(2);
        log.info("{}", set);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            log.info("{}", iterator.next());
        }
    }

    private static void demoIteratorException(){
        fillSet(2);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            iterator.next();
            set.remove("value2");
        }
    }

    private static void demoFirstLastAndPoll(){
        fillSet(2);
        log.info("{}", set);
        log.info("call first() - {}", set.first());
        log.info("{}", set);
        log.info("call last() - {}", set.last());
        log.info("{}", set);
        log.info("call pollFirst - {}", set.pollFirst());
        log.info("{}", set);
        log.info("call pollLast - {}", set.pollLast());
        log.info("{}", set);
    }

    private static void demoSubHeadTail(){
        fillSet(7);
        log.info("{}", set);
        String key0 = "value2";
        String key1 = "value5";
        log.info("call set.subSet({}, {}) - {}", key1, key1, set.subSet(key0, key1));
        log.info("call headSet({}) - {}", key1, set.headSet(key1));
        log.info("call tailSet({}) - {}", key0, set.tailSet(key0));
    }
}

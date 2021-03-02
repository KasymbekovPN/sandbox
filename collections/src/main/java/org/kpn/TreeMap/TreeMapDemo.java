package org.kpn.TreeMap;

import com.sun.source.tree.Tree;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class TreeMapDemo {

    private static final Map<String, Runnable> DEMOS = new HashMap<>(){{
        put("demoFirstKey", TreeMapDemo::demoFirstKey);
        put("demoLastKey", TreeMapDemo::demoLastKey);
        put("demoHeadMap", TreeMapDemo::demoHeadMap);
        put("demoTailMap", TreeMapDemo::demoTailMap);
        put("demoSubMap", TreeMapDemo::demoSubMap);
        put("demoFirstEntry", TreeMapDemo::demoFirstEntry);
        put("demoLastEntry", TreeMapDemo::demoLastEntry);
        put("demoPollFirstEntry", TreeMapDemo::demoPollFirstEntry);
        put("demoPollLastEntry", TreeMapDemo::demoPollLastEntry);
        put("demoCeilingKey", TreeMapDemo::demoCeilingKey);
        put("demoFloorKey", TreeMapDemo::demoFloorKey);
        put("demoLowerKey", TreeMapDemo::demoLowerKey);
        put("demoHigherKey", TreeMapDemo::demoHigherKey);
        put("demoCeilingEntry", TreeMapDemo::demoCeilingEntry);
        put("demoFloorEntry", TreeMapDemo::demoFloorEntry);
        put("demoLowerEntry", TreeMapDemo::demoLowerEntry);
        put("demoHigherEntry", TreeMapDemo::demoHigherEntry);
        put("demoDescendingKeySey", TreeMapDemo::demoDescendingKeySey);
        put("demoDescendingMap", TreeMapDemo::demoDescendingMap);
        put("demoNavigableKeySet", TreeMapDemo::demoNavigableKeySet);
    }};

    private static final TreeMap<String, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) {
//        String key = "demoFirstKey";
//        String key = "demoLastKey";
//        String key = "demoHeadMap";
//        String key = "demoTailMap";
//        String key = "demoSubMap";
//        String key = "demoFirstEntry";
//        String key = "demoLastEntry";
//        String key = "demoPollFirstEntry";
//        String key = "demoPollLastEntry";
//        String key = "demoCeilingKey";
//        String key = "demoFloorKey";
//        String key = "demoLowerKey";
//        String key = "demoHigherKey";
//        String key = "demoCeilingEntry";
//        String key = "demoFloorEntry";
//        String key = "demoLowerEntry";
//        String key = "demoHigherEntry";
//        String key = "demoDescendingKeySey";
//        String key = "demoDescendingMap";
        String key = "demoNavigableKeySet";
        if (DEMOS.containsKey(key)){
            DEMOS.get(key).run();
        } else {
            log.error("Invalid key {}", key);
        }
    }

    private static void fillMap(){
        for (int i = 0; i < 10; i++) {
            treeMap.put(String.format("key%s", i), i);
        }
    }

    // SortedMap
    private static void demoFirstKey(){
        fillMap();
        log.info("call firstKey - {}", treeMap.firstKey());
    }

    // SortedMap
    private static void demoLastKey() {
        fillMap();
        log.info("call lastKey - {}", treeMap.lastKey());
    }

    // SortedMap/NavigableMap
    private static void demoHeadMap(){
        fillMap();
        String key = "key5";
        boolean inclusive = true;
        log.info("call headMap({}) - {}", key, treeMap.headMap(key));
        log.info("call headMap({},{}) with inclusive - {}", key, inclusive, treeMap.headMap(key, inclusive));
    }

    // SortedMap/NavigableMap
    private static void demoTailMap(){
        fillMap();
        String key = "key5";
        boolean inclusive = false;
        log.info("call tailMap({}) - {}", key, treeMap.tailMap(key));
        log.info("call tailMap({}, {}) with inclusive - {}", key, inclusive, treeMap.tailMap(key, inclusive));
    }

    // SortedMap/NavigableMap
    private static void demoSubMap(){
        fillMap();
        String key0 = "key3";
        String key1 = "key7";
        log.info("call subMap({},{}) - {}", key0, key1, treeMap.subMap(key0, key1));
    }

    // NavigableMap - возвращает первый пару “ключ-значение”
    private static void demoFirstEntry(){
        fillMap();
        log.info("call firstEntry() - {}", treeMap.firstEntry());
    }

    // NavigableMap - возвращает последнюю пару “ключ-значение”
    private static void demoLastEntry(){
        fillMap();
        log.info("Call lastEntry() - {}", treeMap.lastEntry());
    }

    // NavigableMap - возвращает и удаляет первую пару
    private static void demoPollFirstEntry(){
        fillMap();
        log.info("before {}", treeMap);
        log.info("call pollFirstEntry() - {}", treeMap.pollFirstEntry());
        log.info("after {}", treeMap);
    }

    // NavigableMap - возвращает и удаляет последнюю пару
    private static void demoPollLastEntry(){
        fillMap();
        log.info("before {}", treeMap);
        log.info("call pollLastEntry() - {}", treeMap.pollLastEntry());
        log.info("after {}", treeMap);
    }

    // NavigableMap - возвращает наименьший ключ k, который больше или равен ключу obj. Если такого ключа нет, возвращает null;
    private static void demoCeilingKey(){
        fillMap();
        String key = "key5";
        log.info("call ceilingKey({}) - {}", key, treeMap.ceilingKey(key));
    }

    // NavigableMap - возвращает самый большой ключ k, который меньше или равен ключу obj. Если такого ключа нет, возвращает null;
    private static void demoFloorKey(){
        fillMap();
        String key = "key5";
        log.info("call floorKey({}) - {}", key, treeMap.floorKey(key));
    }

    // NavigableMap - возвращает наибольший ключ k, который меньше ключа obj. Если такого ключа нет, возвращает null;
    private static void demoLowerKey(){
        fillMap();
        String key = "key3";
        log.info("call lowerKey({}) - {}", key, treeMap.lowerKey(key));
        key = "a";
        log.info("call lowerKey({}) - {}", key, treeMap.lowerKey(key));
    }

    // NavigableMap - возвращает наименьший ключ k, который больше ключа obj. Если такого ключа нет, возвращает null;
    private static void demoHigherKey(){
        fillMap();
        String key = "key7";
        log.info("call higherKey({}) - {}", key, treeMap.higherKey(key));
        key = "key99";
        log.info("call higherKey({}) - {}", key, treeMap.higherKey(key));
    }

    // NavigableMap - аналогичен методу ceilingKey(K obj), только возвращает пару “ключ-значение” (или null);
    private static void demoCeilingEntry(){
        fillMap();
        String key = "key5";
        log.info("call ceilingEntry({}) - {}", key, treeMap.ceilingEntry(key));
    }

    // NavigableMap - аналогичен методу floorKey(K obj), только возвращает пару “ключ-значение” (или null);
    private static void demoFloorEntry(){
        fillMap();
        String key = "key5";
        log.info("call floorEntry({}) - {}", key, treeMap.floorEntry(key));
    }

    // NavigableMap - аналогичен методу lowerKey(K obj), только возвращает пару “ключ-значение” (или null);
    private static void demoLowerEntry(){
        fillMap();
        String key = "key3";
        log.info("call lowerEntry({}) - {}", key, treeMap.lowerEntry(key));
        key = "a";
        log.info("call lowerEntry({}) - {}", key, treeMap.lowerEntry(key));
    }

    // NavigableMap - аналогичен методу higherKey(K obj), только возвращает пару “ключ-значение” (или null);
    private static void demoHigherEntry(){
        fillMap();
        String key = "key7";
        log.info("call higherEntry({}) - {}", key, treeMap.higherEntry(key));
        key = "key99";
        log.info("call higherEntry({}) - {}", key, treeMap.higherEntry(key));
    }

    // NavigableMap - возвращает NavigableSet, содержащий все ключи, отсортированные в обратном порядке;
    private static void demoDescendingKeySey(){
        fillMap();
        log.info("{}", treeMap);
        log.info("NavigableSet {}", treeMap.navigableKeySet());
    }

    // NavigableMap - возвращает NavigableMap, содержащую все пары, отсортированные в обратном порядке;
    private static void demoDescendingMap(){
        fillMap();
        log.info("{}", treeMap);
        log.info("NavigableMap {}", treeMap.descendingMap());
    }

    // NavigableMap - возвращает объект NavigableSet, содержащий все ключи в порядке хранения;
    private static void demoNavigableKeySet(){
        fillMap();
        log.info("{}", treeMap);
        log.info("NavigableSet {}", treeMap.navigableKeySet());
    }
}

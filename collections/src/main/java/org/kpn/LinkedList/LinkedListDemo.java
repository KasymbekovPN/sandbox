package org.kpn.LinkedList;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class LinkedListDemo {

    private static final Map<String, Runnable> DEMOS = new HashMap<String, Runnable>(){{
        put("demoAdd", LinkedListDemo::demoAdd);
        put("demoRemove", LinkedListDemo::demoRemove);
        put("demoIndexOf", LinkedListDemo::demoIndexOf);
        put("demoClear", LinkedListDemo::demoClear);
        put("demoGet", LinkedListDemo::demoGet);
        put("demoSet", LinkedListDemo::demoSet);
        put("demoAddOfferFirstLast", LinkedListDemo::demoAddOfferFirstLast);
        put("demoPoll", LinkedListDemo::demoPoll);
        put("demoPeekElement", LinkedListDemo::demoPeekElement);
        put("demoRemoveFirstLastOccurrence", LinkedListDemo::demoRemoveFirstLastOccurrence);
    }};

    private static final LinkedList<Integer> ll = new LinkedList<>();

    public static void main(String[] args) {
//        String key = "demoAdd";
//        String key = "demoRemove";
//        String key = "demoIndexOf";
//        String key = "demoClear";
//        String key = "demoGet";
//        String key = "demoSet";
//        String key = "demoAddOfferFirstLast";
//        String key = "demoPoll";
//        String key = "demoPeekElement";
        String key = "demoRemoveFirstLastOccurrence";
        if (DEMOS.containsKey(key)){
            DEMOS.get(key).run();
        } else {
            log.error("Invalid key {}", key);
        }
    }

    private static void fillLL(){
        for (int i = 0; i < 10; i++) {
            ll.add(100+i);
        }
    }

    private static void demoAdd(){
        fillLL();
        log.info("{}", ll);
        ll.add(4);
        log.info("{}", ll);
        ll.add(2, 123);
        log.info("{}", ll);
    }

    private static void demoRemove(){
        fillLL();
        log.info("{}", ll);
        log.info("call remove() - {}", ll.remove());
        log.info("{}", ll);
        log.info("call remove({}) - {}", 5, ll.remove(5));
        log.info("{}", ll);
        log.info("call remove({}) - {}", 102, ll.remove(new Integer(102)));
        log.info("{}", ll);
        log.info("call removeFirst() - {}", ll.removeFirst());
        log.info("{}", ll);
        log.info("call removeLast() - {}", ll.removeLast());
        log.info("{}", ll);
    }

    private static void demoIndexOf(){
        fillLL();
        log.info("call indexOf({}) - {}", 105, ll.indexOf(105));
    }

    private static void demoClear(){
        fillLL();
        log.info("{}", ll);
        ll.clear();
        log.info("{}", ll);
    }

    private static void demoGet(){
        fillLL();
        log.info("call getFirst() - {}", ll.getFirst());
        log.info("call getLast() - {}", ll.getLast());
        for (int i = 0; i < 10; i++) {
            log.info("call get({}) - {}", i, ll.get(i));
        }
    }

    private static void demoSet(){
        fillLL();
        log.info("{}", ll);
        log.info("call set({}, {}) - {}", 3, 333, ll.set(3, 333));
        log.info("{}", ll);
    }

    // разница между add и offer в том, что при offer проверяется возможность
    // вставки, если реализация накладывает ограничение по размеру,
    // offer возвращает true, если вставка произошла
    private static  void demoAddOfferFirstLast(){
        fillLL();
        ll.addFirst(999);
        log.info("{}", ll);
        ll.addLast(888);
        log.info("{}", ll);
        ll.offerFirst(777);
        log.info("{}", ll);
        ll.offerLast(666);
        log.info("{}", ll);
    }

    private static void demoPoll(){
        fillLL();
        log.info("{}", ll);
        log.info("call poll() - {}", ll.poll());
        log.info("{}", ll);
        log.info("call pollFirst() - {}", ll.pollFirst());
        log.info("{}", ll);
        log.info("call pollLast() - {}", ll.pollLast());
        log.info("{}", ll);
    }

    // peek, element возвращают не удаляя начальный элемент,
    // но если очередь пуста, то peek вернет null,
    // а element бросит исключение.
    private static void demoPeekElement(){
        fillLL();
        log.info("call peek() - {}", ll.peek());
        log.info("call element() - {}", ll.element());
        log.info("call peekFirst() - {}", ll.peekFirst());
        log.info("call peekLast() - {}", ll.peekLast());
    }

    private static void demoRemoveFirstLastOccurrence(){
        fillLL();
        log.info("call removeFirstOccurrence({}) - {}", 1, ll.removeFirstOccurrence(1));
        log.info("call removeFirstOccurrence({}) - {}", 101, ll.removeFirstOccurrence(101));
        log.info("call removeLastOccurrence({}) - {}", 2, ll.removeLastOccurrence(2));
        log.info("call removeLastOccurrence({}) - {}", 102, ll.removeLastOccurrence(102));
    }
}

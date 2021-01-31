package org.kpn.Thread3;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class DeadLockDemo {

    private static class Friend{
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower){
            log.info("{} : {} has bower to me", name, bower.name);
            bower.backBow(this);
        }

        public synchronized void backBow(Friend bower){
            log.info("{} : {} has bower back to me", name, bower.name);
        }
    }

    public static void main(String[] args) {
        final Friend f1 = new Friend("F1");
        final Friend f2 = new Friend("F2");
        new Thread(() -> f1.bow(f2)).start();
        new Thread(() -> f2.bow(f1)).start();
    }
}

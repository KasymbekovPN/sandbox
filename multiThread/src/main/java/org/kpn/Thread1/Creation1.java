package org.kpn.Thread1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Creation1 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        log.info("done");
    }

    private static class MyThread extends Thread{
        @Override
        public void run() {
            // !!! not single responsibility !!!
            log.info("MyThread is running");
        }
    }
}

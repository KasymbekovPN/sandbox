package org.kpn.Thread1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Creation2 {

    public static void main(String[] args) {
        Runnable r = () -> log.info("r in running");
        Thread thread = new Thread(r);
        thread.start();
        log.info("done");
    }
}

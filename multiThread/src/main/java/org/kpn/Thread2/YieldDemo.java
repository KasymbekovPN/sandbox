package org.kpn.Thread2;

public class YieldDemo extends Thread{

    public YieldDemo() {
        this.start();
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " is yielding own place");
        Thread.yield();
        System.out.println(name + " has finished executing");
    }

    public static void main(String[] args) {
        new YieldDemo();
        new YieldDemo();
        new YieldDemo();
    }
}

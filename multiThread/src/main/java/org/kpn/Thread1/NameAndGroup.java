package org.kpn.Thread1;

public class NameAndGroup {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("thread: " + thread.getName());
        System.out.println("group: " + thread.getThreadGroup().getName());
        System.out.println("parent name: " + thread.getThreadGroup().getParent().getName());
    }
}

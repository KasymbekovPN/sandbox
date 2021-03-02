package org.kpn.ch3.parentAndChildCtx;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HierarchicalAppContextUsage {
    public static void main(String[] args) {
        GenericXmlApplicationContext parentCtx = new GenericXmlApplicationContext();
        parentCtx.load("parentAndChildCtx/parent-context.xml");
        parentCtx.refresh();

        GenericXmlApplicationContext childCtx = new GenericXmlApplicationContext();
        childCtx.load("parentAndChildCtx/child-context.xml");
        childCtx.setParent(parentCtx);
        childCtx.refresh();

        Song song1 = childCtx.getBean("song1", Song.class);
        Song song2 = childCtx.getBean("song2", Song.class);
        Song song3 = childCtx.getBean("song3", Song.class);

        System.out.println(song1.getTitle());
        System.out.println(song2.getTitle());
        System.out.println(song3.getTitle());

        childCtx.close();
        parentCtx.close();
    }
}

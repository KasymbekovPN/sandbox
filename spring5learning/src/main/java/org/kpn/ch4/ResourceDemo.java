package org.kpn.ch4;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ResourceDemo {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();

        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();

        System.out.println(file.getPath());

        Resource res1 = ctx.getResource("file:///" + file.getPath());
        display(res1);

        Resource res2 = ctx.getResource("classpath:test.txt");
        display(res2);

        Resource res3 = ctx.getResource("https://google.com");
        display(res3);

        ctx.close();
    }

    private static void display(Resource res) throws IOException {
        System.out.println(res.getClass());
        System.out.println(res.getURL().getContent());
        System.out.println("");
    }
}

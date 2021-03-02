package org.kpn.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

public class DestructiveBeanWithInterface {

    private File file;
    private String filePath;

    @PostConstruct
    public void init() throws IOException {
        System.out.println("Initializing bean");

        if (filePath == null){
            throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBeanWithInterface.class);
        }

        this.file = new File(filePath);
        file.createNewFile();

        System.out.println("File exists: " + file.exists());
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Destroying bean");

        if (!file.delete()){
            System.err.println("ERROR: failed to delete file");
        }

        System.out.println("File exists: " + file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("ch4/app-context-xml-destroy-AppCtxAware.xml");
        ctx.registerShutdownHook();
        ctx.refresh();

        ctx.getBean("destructiveBean", DestructiveBeanWithInterface.class);
    }

}

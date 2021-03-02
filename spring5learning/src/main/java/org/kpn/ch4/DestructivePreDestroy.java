package org.kpn.ch4;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

public class DestructivePreDestroy {

    private File file;
    private String filePath;

    @PostConstruct
    public void init() throws IOException {
        System.out.println("Initializing bean");

        if (filePath == null){
            throw new IllegalArgumentException("You must specify the filePath property of " + DestructivePreDestroy.class);
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
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-destroy-preDestroy0.xml");

        DestructivePreDestroy destructiveDean = (DestructivePreDestroy) ctx.getBean("destructiveBean");

        endIt(ctx);
    }

    private static void endIt(GenericXmlApplicationContext ctx){

        // deprecated
//        ctx.destroy();

        // close
//        ctx.close();

        // hook
        ctx.registerShutdownHook();
    }
}

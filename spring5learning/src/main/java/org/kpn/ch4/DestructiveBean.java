package org.kpn.ch4;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

public class DestructiveBean implements InitializingBean {

    private File file;
    private String filePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");

        if (filePath == null){
            throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBean.class);
        }

        this.file = new File(filePath);
        file.createNewFile();

        System.out.println("File exists: " + file.exists());
    }

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
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("ch4/app-context-xml-destroy-interface0.xml");

        DestructiveBean destructiveDean = (DestructiveBean) ctx.getBean("destructiveBean");
        System.out.println("Calling destroy()");
//        ctx.destroy();
        //<
        ctx.close();
        System.out.println("called destroy()");
    }
}

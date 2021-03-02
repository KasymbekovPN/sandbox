package org.kpn.ch3.xml.singer;

import org.kpn.ch3.xml.guitar.Guitar;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AwareSinger implements ApplicationContextAware {

    private Guitar guitar;

    ApplicationContext ctx;

    public AwareSinger() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void sing(){
        guitar = ctx.getBean("gopher", Guitar.class);
        guitar.sing();
    }
}

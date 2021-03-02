package org.kpn.ch3.xml.demoBean;

import org.kpn.ch3.xml.singer.Singer;

public abstract class AbstractLookupDemoBean implements DemoBean{

    public abstract Singer getMySinger();

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}

package org.kpn.ch3.xml.demoBean;

import org.kpn.ch3.xml.singer.Singer;

public class StandardLookupDemoBean implements DemoBean{

    private Singer mySinger;

    public void setMySinger(Singer mySinger) {
        this.mySinger = mySinger;
    }

    @Override
    public Singer getMySinger() {
        return mySinger;
    }

    @Override
    public void doSomething() {
        mySinger.sing();
    }
}

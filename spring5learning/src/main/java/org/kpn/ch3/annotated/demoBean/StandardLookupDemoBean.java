package org.kpn.ch3.annotated.demoBean;

import org.kpn.ch3.annotated.singer.ProtoSinger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean {

    private ProtoSinger mySinger;

    @Autowired
    @Qualifier("protoSinger")
    public void setMySinger(ProtoSinger mySinger) {
        this.mySinger = mySinger;
    }

    @Override
    public ProtoSinger getMySinger() {
        return mySinger;
    }

    @Override
    public void doSomething() {
        mySinger.sing();
    }
}

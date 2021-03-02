package org.kpn.ch3.annotated.demoBean;

import org.kpn.ch3.annotated.singer.ProtoSinger;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements DemoBean {

    @Lookup("protoSinger")
    @Override
    public ProtoSinger getMySinger() {
        return null;
    }

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}

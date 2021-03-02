package org.kpn.ch3.annotated.singer;

import org.kpn.ch3.annotated.Inspiration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("singer")
public class Singer {

    @Autowired
    private Inspiration inspirationBean;

    public void sing(){
        System.out.println("... " + inspirationBean.getLyric());
    }
}

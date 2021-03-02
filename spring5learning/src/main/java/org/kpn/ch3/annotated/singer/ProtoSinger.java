package org.kpn.ch3.annotated.singer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("protoSinger")
@Scope("prototype")
public class ProtoSinger {

    private String lyric = "I played a quick game of chess with the salt and pepper shaker";

    public void sing(){
//        System.out.println(lyric);
    }
}

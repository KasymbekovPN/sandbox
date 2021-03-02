package org.kpn.ch3.xml.holder;

import org.springframework.stereotype.Service;

@Service("lyricHolder")
public class LyricHolder implements ContentHolder{

    private String value = "'You be the DJ, I'll be the driver'";

    @Override
    public String toString() {
        return "LyricHolder{" +
                "value='" + value + '\'' +
                '}';
    }
}

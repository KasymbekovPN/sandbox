package org.kpn.ch5;

public class Guitarist implements Singer{

    private String lyric = "You've gonna live forever in me";

    @Override
    public void sing() {
        System.out.println(lyric);
    }
}

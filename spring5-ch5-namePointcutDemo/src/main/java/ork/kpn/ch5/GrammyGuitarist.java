package ork.kpn.ch5;

import org.kpn.ch5.Singer;

public class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me\nAnd gravity wants to bring me down");
    }

    public void sing(Guitar guitar){
        System.out.println("play: " + guitar.play());
    }

    public void rest(){
        System.out.println("zzz");
    }

    public void talk(){
        System.out.println("talk");
    }
}

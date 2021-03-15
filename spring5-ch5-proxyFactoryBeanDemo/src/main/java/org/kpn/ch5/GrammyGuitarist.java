package org.kpn.ch5;

public class GrammyGuitarist implements Singer1 {

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

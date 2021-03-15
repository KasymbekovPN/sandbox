package org.kpn.ch5;

public class Documentarist {
    protected GrammyGuitarist guitarist;

    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

    public void execute(){
        guitarist.sing();
        guitarist.talk();
    }
}

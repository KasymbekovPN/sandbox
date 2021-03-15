package org.kpn.ch5;

public class NewDocumentarist extends Documentarist{
    @Override
    public void execute() {
        guitarist.sing();
        guitarist.sing(new Guitar());
        guitarist.talk();
    }
}

package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mediator")
public class PatternMediator {

    private interface Mediator{
        void send(String message, Colleague sender);
    }

    private static abstract class Colleague{

        protected final Mediator mediator;

        public Colleague(Mediator mediator) {
            this.mediator = mediator;
        }

        public void send(String message){
            mediator.send(message, this);
        }

        public abstract void notify(String message);
    }

    private static class Colleague1 extends Colleague{
        public Colleague1(Mediator mediator) {
            super(mediator);
        }

        @Override
        public void notify(String message) {
            System.out.println("Colleague1 gets message: " + message);
        }
    }

    private static class Colleague2 extends Colleague{
        public Colleague2(Mediator mediator) {
            super(mediator);
        }

        @Override
        public void notify(String message) {
            System.out.println("Colleague2 gets message: " + message);
        }
    }

    private static class MediatorImpl implements Mediator{
        private Colleague colleague1;
        private Colleague colleague2;

        public void setColleague1(Colleague colleague1) {
            this.colleague1 = colleague1;
        }

        public void setColleague2(Colleague colleague2) {
            this.colleague2 = colleague2;
        }

        @Override
        public void send(String message, Colleague sender) {
            if (sender.equals(colleague1)){
                colleague2.notify(message);
            } else {
                colleague1.notify(message);
            }
        }
    }

    @Test
    void test(){

        MediatorImpl mediator = new MediatorImpl();

        Colleague c1 = new Colleague1(mediator);
        Colleague2 c2 = new Colleague2(mediator);

        mediator.setColleague1(c1);
        mediator.setColleague2(c2);

        c1.send("How are you?");
        c2.send("Fine, thanks");
    }
}

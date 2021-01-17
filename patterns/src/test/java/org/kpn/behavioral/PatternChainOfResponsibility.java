package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Chain of responsibility")
public class PatternChainOfResponsibility {


    private static abstract class Logger{
        private static final int ERR = 3;
        private static final int NOTICE = 5;
        private static final int DEBUG = 7;

        protected int mask;
        protected Logger next;

        public Logger(int mask) {
            this.mask = mask;
        }

        public Logger setNext(Logger log) {
            this.next = log;
            return log;
        }

        public void message(String msg, int priority){
            if (priority <= mask){
                writeMessage(msg);
            }

            if (next != null){
                next.message(msg, priority);
            }
        }

        abstract protected void writeMessage(String msg);
    }

    public static class StdoutLogger extends Logger{

        public StdoutLogger(int mask) {
            super(mask);
        }

        @Override
        protected void writeMessage(String msg) {
            System.out.println("Writing to stdout: " + msg);
        }
    }

    public static class EmailLogger extends Logger{

        public EmailLogger(int mask) {
            super(mask);
        }

        @Override
        protected void writeMessage(String msg) {
            System.out.println("Sending via email:" + msg);
        }
    }

    public static class StderrLogger extends Logger{
        public StderrLogger(int mask) {
            super(mask);
        }

        @Override
        protected void writeMessage(String msg) {
            System.out.println("Sending to stderr: " + msg);
        }
    }

    @Test
    void test(){
        StdoutLogger logger = new StdoutLogger(Logger.DEBUG);
        logger
                .setNext(new EmailLogger(Logger.NOTICE))
                .setNext(new StderrLogger(Logger.ERR));

        // handled by StdoutLogger
        logger.message("message1", Logger.DEBUG);

        // handled by StdoutLogger and EmailLogger
        logger.message("message2", Logger.NOTICE);

        // handled by all loggers
        logger.message("message3", Logger.ERR);
    }
}

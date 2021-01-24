package org.kpn.structural;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Facade")
public class PatternFacade {

    private static class CPU{
        void freeze() {}
        void jump(long position){}
        void execute(){}
    }

    private static class Memory{
        void load(long position, byte[] data){}
    }

    private static class HardDrive{
        byte[] read(long lba, int size){
            return new byte[]{1,2,3,4};
        }
    }

    private static class Computer{
        private final CPU cpu;
        private final Memory memory;
        private final HardDrive hardDrive;

        public Computer(CPU cpu, Memory memory, HardDrive hardDrive) {
            this.cpu = cpu;
            this.memory = memory;
            this.hardDrive = hardDrive;
        }

        public void  start(){
            /* do sth */
        }
    }
}

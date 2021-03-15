package org.kpn.ch5;

import java.util.Random;

public class KeyGenerator {

    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;

    private final Random rand = new Random();

    public long getKey(){
        int x = rand.nextInt(3);

        return x == 1 ? WEAK_KEY : STRONG_KEY;
    }
}

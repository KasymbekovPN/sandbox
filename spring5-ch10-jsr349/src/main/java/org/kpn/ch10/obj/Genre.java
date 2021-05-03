package org.kpn.ch10.obj;

public enum Genre {
    POP("P"),
    JAZZ("J"),
    BLUES("B"),
    COUNTRY("C");

    private final String code;

    private Genre(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}

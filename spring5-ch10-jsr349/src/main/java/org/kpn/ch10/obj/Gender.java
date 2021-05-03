package org.kpn.ch10.obj;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String code;

    private Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

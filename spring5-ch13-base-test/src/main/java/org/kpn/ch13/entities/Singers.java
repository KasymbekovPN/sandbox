package org.kpn.ch13.entities;

import java.io.Serializable;
import java.util.List;

public class Singers implements Serializable {

    private List<Singer> singers;

    public Singers() {
    }

    public Singers(List<Singer> singers) {
        this.singers = singers;
    }

    public List<Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        return "Singers{" +
                "singers=" + singers +
                '}';
    }
}

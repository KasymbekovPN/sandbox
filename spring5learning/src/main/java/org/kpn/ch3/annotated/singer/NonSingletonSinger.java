package org.kpn.ch3.annotated.singer;

import java.util.Objects;

public class NonSingletonSinger {

    private String name;

    public NonSingletonSinger(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NonSingletonSinger{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonSingletonSinger that = (NonSingletonSinger) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

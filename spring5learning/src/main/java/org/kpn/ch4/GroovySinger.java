package org.kpn.ch4;

public class GroovySinger {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "GroovySinger{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

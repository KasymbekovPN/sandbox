package org.kpn.ch16.util;

public class Message {

    private final String type;
    private final String message;

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}

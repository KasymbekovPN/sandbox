package org.kpn.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order{

    private boolean valid = true;
    private String id = "some_id";
    private String name = "someName";
    private String email = "some@email.com";
    private List<Item> items = new ArrayList<>();

    public boolean isValid(){
        return valid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Item> getItems() {
        return items;
    }
}

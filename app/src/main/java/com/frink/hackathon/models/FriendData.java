package com.frink.hackathon.models;

import java.io.Serializable;

/**
 * Created by amandeepsingh on 16/10/15.
 */
public class FriendData implements Serializable {
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

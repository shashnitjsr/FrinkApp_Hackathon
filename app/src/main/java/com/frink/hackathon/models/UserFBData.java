package com.frink.hackathon.models;

import java.io.Serializable;

/**
 * Created by amandeepsingh on 16/10/15.
 */
public class UserFBData implements Serializable {
    private String email;
    private String name;
    private String Id;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}

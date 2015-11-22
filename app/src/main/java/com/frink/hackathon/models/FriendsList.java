package com.frink.hackathon.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amandeepsingh on 17/10/15.
 */
public class FriendsList implements Serializable {
    private ArrayList<FriendName> users;

    public ArrayList<FriendName> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<FriendName> users) {
        this.users = users;
    }

    public static class FriendName implements Serializable {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}



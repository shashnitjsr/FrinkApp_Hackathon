package com.frink.hackathon.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amandeepsingh on 16/10/15.
 */
public class UserFBFriendList implements Serializable {
    private ArrayList<FriendData> data;

    public ArrayList<FriendData> getData() {
        return data;
    }

    public void setData(ArrayList<FriendData> data) {
        this.data = data;
    }
}



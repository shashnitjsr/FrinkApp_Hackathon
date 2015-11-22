package com.frink.hackathon.task;

import android.os.AsyncTask;

import com.frink.hackathon.models.FriendsList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by amandeepsingh on 17/10/15.
 */


public class GetFriendsWithCardAsyncTask extends AsyncTask<String, Void, FriendsList> {

    private GetFriendsWithCardCallBack callBack;

    public GetFriendsWithCardAsyncTask(GetFriendsWithCardCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected FriendsList doInBackground(String... parmas) {
        FriendsList friendsList = null;
        InputStream is = null;
        try {
            URL url = new URL(parmas[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            is = conn.getInputStream();
            final Gson gson = new Gson();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            friendsList = gson.fromJson(reader, FriendsList.class);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return friendsList;
    }

    @Override
    protected void onPostExecute(FriendsList friendsList) {
        System.out.println("onPost");
        if (friendsList != null) {
            if (callBack != null) {
                callBack.onSuccess(friendsList);
            } else {

                callBack.onFailure();
            }
        } else {
            callBack.onFailure();
        }

    }

    public interface GetFriendsWithCardCallBack {
        void onSuccess(FriendsList friendsList);

        void onFailure();
    }
}


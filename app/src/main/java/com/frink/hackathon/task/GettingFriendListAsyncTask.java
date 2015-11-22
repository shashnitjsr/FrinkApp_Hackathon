package com.frink.hackathon.task;

import android.os.AsyncTask;

import com.frink.hackathon.models.UserFBFriendList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by amandeepsingh on 16/10/15.
 */

public class GettingFriendListAsyncTask extends AsyncTask<String, Void, InputStream> {

    private Class clazz;
    private GettingFriendListCallBack callBack;

    public GettingFriendListAsyncTask(Class clazz, GettingFriendListCallBack callBack) {
        this.clazz = clazz;
        this.callBack = callBack;
    }

    @Override
    protected InputStream doInBackground(String... parmas) {

        InputStream is = null;
        try {
            URL url = new URL(parmas[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            is = conn.getInputStream();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return is;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {

        final Gson gson = new Gson();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        if (reader != null) {
            callBack.onSuccess((UserFBFriendList) gson.fromJson(reader, clazz));
        } else {
            callBack.onFailure();
        }

    }

    public interface GettingFriendListCallBack {
        void onSuccess(UserFBFriendList list);

        void onFailure();
    }
}


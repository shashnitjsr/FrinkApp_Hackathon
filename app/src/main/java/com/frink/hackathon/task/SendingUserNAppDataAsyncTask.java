package com.frink.hackathon.task;

import android.os.AsyncTask;

import com.frink.hackathon.models.FriendData;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

/**
 * Created by amandeepsingh on 16/10/15.
 */

public class SendingUserNAppDataAsyncTask extends AsyncTask<Void, String, String> {


    private String name;
    private String id;
    private String mail;
    private ArrayList<String> apps;
    private ArrayList<FriendData> friends;
    private String base = "http://khandeshb.housing.com:5678/";
    private String post = "api/v0/details";
    private OnMessageSent callback;

    public SendingUserNAppDataAsyncTask(String name, String id, String mail, ArrayList<String> apps, ArrayList<FriendData> friends, OnMessageSent callback) {
        this.name = name;
        this.id = id;
        this.mail = mail;
        this.apps = apps;
        this.friends = friends;
        this.callback = callback;

    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            SendMessageJsonBuilder jsonBuilder = new SendMessageJsonBuilder();
            jsonBuilder.name = name;
            jsonBuilder.email = mail;
            jsonBuilder.id = id;
            jsonBuilder.friends = new ArrayList<>();
            jsonBuilder.friends.addAll(friends);
            jsonBuilder.apps = new ArrayList<>();
            jsonBuilder.apps.addAll(apps);


            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpost = new HttpPost(base + post);
            Gson gson = new Gson();
            String json = gson.toJson(jsonBuilder);
            StringEntity se = new StringEntity(json.toString());
            httpost.setEntity(se);
            httpost.setHeader("Accept", "application/json");
            httpost.setHeader("Content-type", "application/json");
            HttpResponse response = httpclient.execute(httpost);
            return EntityUtils.toString(response.getEntity());

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(String httpStatusCode) {
        if (httpStatusCode != null) {
            if (callback != null) {
                callback.onSendSuccess();
            } else {
                callback.onSendFailure();
            }
        } else {
            callback.onSendFailure();
        }
    }


    public interface OnMessageSent {

        void onSendSuccess();

        void onSendFailure();
    }

    private class SendMessageJsonBuilder {

        String name, email, id;
        ArrayList<FriendData> friends;
        ArrayList<String> apps;
    }

}

package com.frink.hackathon.task;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shashwatsinha on 17/10/15.
 */
public class UserFirstTimeLogin extends AsyncTask<Void, Void, UserFirstTimeLogin.Results> {

    private String base = "http://khandeshb.housing.com:5678/";
    private String post = "api/v0/get_card";
    private Callback callback;
    private String id;

    public UserFirstTimeLogin(Callback cb, String id) {
        callback = cb;
        this.id = id;
    }

    @Override
    protected Results doInBackground(Void... params) {
        InputStream is;
        try {
            URL url = new URL(base + post + "?fb_id=" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            is = conn.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String str;
            final Gson gson = new Gson();
            Results r = gson.fromJson(reader, Results.class);
            return r;

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPostExecute(Results p) {
        if (p != null) {
            boolean result = p.getResults();
            callback.onSuccess(result);
        }
    }

    static public class Results {
        private boolean results;

        public boolean getResults() {
            return results;
        }
    }

    public interface Callback {
        void onSuccess(boolean result);
    }
}

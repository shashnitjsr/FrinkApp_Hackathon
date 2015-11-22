package com.frink.hackathon.coupanlist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.frink.hackathon.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by shashwatsinha on 16/10/15.
 */
public class CardListFragment extends Activity {

    public LinkedList<CardListModel.CardModel> items = new LinkedList<CardListModel.CardModel>();
    CardListAdapater cl;
    CardListModel.CardModel cm = new CardListModel.CardModel(null, null, null, null, null, null);
    String userId;
    ListView listView;


    @Override
    public void onCreate(Bundle onSaveInstance) {
        super.onCreate(onSaveInstance);
        setContentView(R.layout.card_list_view);

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");

        listView = (ListView) findViewById(R.id.list_view);


        cl = new CardListAdapater(this, R.layout.card_list_row, items);
        listView.setAdapter(cl);
        JsonTask task = new JsonTask(this, cl);


        String userid = "http://khandeshb.housing.com:5678/api/v0/get_coupons_of_friends?fb_id=";
        userid += userId;
        //userid += "912790462141979";
        Log.d("shashwat", "userid is " + userid);

        task.execute(userid);

    }


    /**
     * Created by shashwatsinha on 16/10/15.
     */
    public static class JsonTask extends AsyncTask<String, Void, CardListModel> {

        CardListAdapater cardListAdapater;
        ProgressDialog progressDialogue;
        Context context;

        public JsonTask(Context context, CardListAdapater cl) {
            progressDialogue = new ProgressDialog(context);
            progressDialogue.setTitle("Please Wait");
            progressDialogue.setMessage("Data getting Loaded");
            progressDialogue.show();
            cardListAdapater = cl;
        }


        @Override
        protected CardListModel doInBackground(String... parmas) {


            CardListModel cl = null;
            InputStream is;
            try {
                URL url = new URL(parmas[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                is = conn.getInputStream();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String str;
                final Gson gson = new Gson();
                cl = gson.fromJson(reader, CardListModel.class);
                return cl;

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return cl;
        }

        @Override
        public void onPostExecute(CardListModel cl) {
            progressDialogue.dismiss();
            ArrayList<CardListModel.CardModel> cards = cl.getCoupans();
            Iterator<CardListModel.CardModel> ie = cards.iterator();

            while (ie.hasNext()) {
                CardListModel.CardModel cm = ie.next();

                cardListAdapater.add(cm);
            }
            cardListAdapater.notifyDataSetChanged();

        }

    }
}

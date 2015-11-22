package com.frink.hackathon.addcardlist;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashwatsinha on 17/10/15.
 */
public class BankCardLoadAsyncTask extends AsyncTask<String, Void, BackCardTypeModel> {

    BackCardTypeModel backCardTypeModel;
    Spinner spinner;
    Context context;
    ListView listView;
    int positionOfSpinner;
    String userId;
    ProgressDialog progressDialogue;
    FragmentManager fragmentManager;

    BankCardLoadAsyncTask(Context context, ListView listView, Spinner spinner, String userId, FragmentManager fragmentManager) {
        this.spinner = spinner;
        this.context = context;
        this.listView = listView;
        this.userId = userId;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onPreExecute() {
        progressDialogue = new ProgressDialog(context);
        progressDialogue.setTitle("Please Wait");
        progressDialogue.setMessage("Data getting Loaded");
        progressDialogue.show();
    }

    @Override
    protected BackCardTypeModel doInBackground(String... params) {
        InputStream is;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            is = conn.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String str;
            final Gson gson = new Gson();
            backCardTypeModel = gson.fromJson(reader, BackCardTypeModel.class);
            return backCardTypeModel;

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return backCardTypeModel;
    }

    @Override
    public void onPostExecute(BackCardTypeModel bankCardTypeModel) {
        progressDialogue.dismiss();
        if (bankCardTypeModel != null) {

            List<BackCardTypeModel.BankCards> bankCards = bankCardTypeModel.getChacha();
            ArrayList<String> list = new ArrayList<String>();
            final List<ArrayList<String>> list_cards = new ArrayList<ArrayList<String>>();
            final List<ArrayList<Integer>> list_cards_id = new ArrayList<ArrayList<Integer>>();
            for (BackCardTypeModel.BankCards card : bankCards) {
                list.add(card.getName());
                list_cards.add(card.bankCards());
                list_cards_id.add(card.bankCardIds());
            }
            spinner.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list));

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    positionOfSpinner = i;
                    ArrayList<String> list = list_cards.get(i);
                    listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_activated_1, list));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    progressDialogue.dismiss();
                    new PostCardServer(context, userId, Integer.toString(list_cards_id.get(positionOfSpinner).get(i)), fragmentManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            });
        }
    }


}

package com.frink.hackathon.addcardlist;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import com.frink.hackathon.R;

/**
 * Created by shashwatsinha on 16/10/15.
 */
public class BankCardFragment extends Activity {

    private Spinner bankListView;
    private ListView listView;
    String url = "http://khandeshb.housing.com:5678/api/v0/list_card";
    String userId;


    /*  public static BankCardFragment getInstance(String id) {
          BankCardFragment bankCardFragment = new BankCardFragment();
          Bundle b = new Bundle();
          b.putString("id", id);
          bankCardFragment.setArguments(b);
          return bankCardFragment;
      }
  */
    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.bankcard_layout);

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");
        bankListView = (Spinner) findViewById(R.id.bank_spinner_bank_name);
        listView = (ListView) findViewById(R.id.listview_bank);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new BankCardLoadAsyncTask(this, listView, bankListView, userId, getFragmentManager()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);

    }

}

package com.frink.hackathon.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.frink.hackathon.R;
import com.frink.hackathon.addcardlist.BankCardFragment;
import com.frink.hackathon.coupanlist.CardListFragment;
import com.frink.hackathon.task.UserFirstTimeLogin;

/**
 * Created by amandeepsingh on 17/10/15.
 */
public class LandingScreenFragment extends Activity implements View.OnClickListener, UserFirstTimeLogin.Callback {
    private String id;
    private Button button1, button2;
    private ProgressDialog progressDialogue;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen_layout);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("shashwat", "getFragmentManager().getBackStackEntryCount() in LandingScreenFragment " + getFragmentManager().getBackStackEntryCount());
        Log.d("shashwat", "LandingScreenFragment onResume" +
                "");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                progressDialogue = new ProgressDialog(this);
                progressDialogue.setTitle("Please Wait");
                progressDialogue.setMessage("Data getting Loaded");
                progressDialogue.show();
                new UserFirstTimeLogin(this, id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                break;
            case R.id.button2:

                Intent intent = new Intent(LandingScreenFragment.this, CardListFragment.class);
                intent.putExtra("id", id);
                LandingScreenFragment.this.startActivity(intent);


                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("shashwat", "LandingScreenFragment onDestroy" +
                "");
    }

    @Override
    public void onSuccess(boolean result) {
        progressDialogue.dismiss();
        Log.d("shashwat", "getFragmentManager() LandingScreenFragment " + getFragmentManager());
        if (result) {
            Intent intent = new Intent(LandingScreenFragment.this, FriendListFragment.class);
            intent.putExtra("id", id);
            LandingScreenFragment.this.startActivity(intent);
        } else {
            Intent intent = new Intent(LandingScreenFragment.this, BankCardFragment.class);
            intent.putExtra("id", id);
            LandingScreenFragment.this.startActivity(intent);
        }
    }
}

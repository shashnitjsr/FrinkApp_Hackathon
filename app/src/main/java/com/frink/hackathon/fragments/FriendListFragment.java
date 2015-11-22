package com.frink.hackathon.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.frink.hackathon.R;
import com.frink.hackathon.models.FriendsList;
import com.frink.hackathon.task.GetFriendsWithCardAsyncTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by amandeepsingh on 17/10/15.
 */
public class FriendListFragment extends Activity implements GetFriendsWithCardAsyncTask.GetFriendsWithCardCallBack {

    private AutoCompleteTextView autoComplete;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> suggesstions = new ArrayList<>();
    private String var = null;
    private GetFriendsWithCardAsyncTask task = null;
    private ListView friendList;
    private ArrayList<FriendsList.FriendName> list = new ArrayList<>();
    private FriendListAdapter listAdapter;
    private String id;
    private ProgressDialog progressDialogue;


    /* static public FriendListFragment getInstance(String id) {
         FriendListFragment cf = new FriendListFragment();

         Bundle bundle = new Bundle();
         bundle.putString("id", id);
         cf.setArguments(bundle);
         return cf;
     }
 */
    @Override
    public void onResume() {
        super.onResume();
        Log.d("shashwat", "getFragmentManager(). getBackStackEntery Vount " + getFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list_fragment);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        setSeugesstionText();
        friendList = (ListView) findViewById(R.id.list_view);
        listAdapter = new FriendListAdapter(list, this);
        friendList.setAdapter(listAdapter);
        adapter = new ArrayAdapter<String>(this, R.layout.filter_friend_seed, suggesstions);
        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        autoComplete.setAdapter(adapter);
        autoComplete.setThreshold(1);

        autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                var = adapter.getItem(position).toString();
                if (var != null) {
                    callApi(var);
                }
            }
        });
        autoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                var = null;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    private void callApi(String str) {
        String var = convert(str);
        if (task == null && var != null && id != null) {
            progressDialogue = new ProgressDialog(this);
            progressDialogue.setTitle("Please Wait");
            progressDialogue.setMessage("Data getting Loaded");
            progressDialogue.show();
            task = new GetFriendsWithCardAsyncTask(this);
            task.execute(
                    "http://khandeshb.housing.com:5678/friends?card=" + var + "&fb_id=" + id);
        }


    }

    private String convert(String var) {
        try {
            String str = URLEncoder.encode(var, "UTF-8");
            return str;
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Exception while building URL, encoding not supported", uee);

        }

    }

    private void setSeugesstionText() {
        suggesstions.add("American Express Platinum Travel Card");
        suggesstions.add("IndusInd Bank Chelsea FC Card");
        suggesstions.add("SBI Signature Card");
        suggesstions.add("Jet Airways ICICI Bank Rubyx VISA Card");
        suggesstions.add("Kotak Delight Credit Card");
        suggesstions.add("HSBC Visa Platinum Card");
        suggesstions.add("DCB Payless Credit Card FD Mandatory");
        suggesstions.add("American Express Gold Card");
        suggesstions.add("IndusInd Bank Signature Card");
        suggesstions.add("Jet Airways IndusInd Bank Voyage American Express Card");
        suggesstions.add("American Express  MakeMyTrip Card");
        suggesstions.add("American Express Platinum Reserve Card");
        suggesstions.add("SBI Simply Save Card");
        suggesstions.add("Jet Airways American Express Platinum Card");
        suggesstions.add("American Express PAYBACK Card");
        suggesstions.add("Citibank Rewards Card");
        suggesstions.add("SBI Platinum Card");
        suggesstions.add("Air India SBI Platinum Card");
        suggesstions.add("Jet Airways IndusInd Bank Voyage Visa Card");
        suggesstions.add("Citibank PremierMiles Card");
        suggesstions.add("HDFC Bank Diners Premium Card");
        suggesstions.add("JetPrivilege HDFC Bank World Card");
        suggesstions.add("ICICI Bank Platinum Chip VISA Card");
        suggesstions.add("Air India SBI Signature Card");
        suggesstions.add("Kotak Royale Signature Credit Card");
        suggesstions.add("ICICI Bank Coral VISA Card");
        suggesstions.add("Kotak PVR Gold Credit Card");
        suggesstions.add("ICICI Bank HPCL Coral VISA Card");
        suggesstions.add("Kotak PVR Platimum Credit Card");
        suggesstions.add("Jet Airways ICICI Bank Coral VISA Card");
        suggesstions.add("HDFC Bank All Miles Card");
        suggesstions.add("HDFC Bank Diners Rewardz Card");
        suggesstions.add("HDFC Bank MoneyBack Card");
        suggesstions.add("ICICI Bank Rubyx Card");
        suggesstions.add("ICICI Bank Sapphiro Card");
        suggesstions.add("Jet Airways ICICI Bank Sapphiro VISA Card");
        suggesstions.add("IndusInd Bank Platinum Card");
        suggesstions.add("Citibank Cashback Card");
        suggesstions.add("IndianOil Citibank Platinum Card");
        suggesstions.add("HDFC Bank Regalia Card");
    }


    @Override
    public void onSuccess(FriendsList friendList) {
        progressDialogue.dismiss();
        if (friendList.getUsers().size() == 0) {
            FriendsList.FriendName name = new FriendsList.FriendName();
            name.setName("No Results Found");
            friendList.getUsers().add(name);
            listAdapter.setList(friendList.getUsers());
        } else {
            listAdapter.setList(friendList.getUsers());
        }
        listAdapter.notifyDataSetChanged();
        task = null;

    }

    @Override
    public void onFailure() {


    }


}

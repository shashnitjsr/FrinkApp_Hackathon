package com.frink.hackathon.addcardlist;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.frink.hackathon.R;
import com.frink.hackathon.fragments.FriendListFragment;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by shashwatsinha on 17/10/15.
 */
public class PostCardServer extends AsyncTask<Void, Void, String> {


    private String userid;
    private String cardId;
    private String base = "http://khandeshb.housing.com:5678/";
    private String post = "api/v0/post_card";
    private OnMessageSent callback;
    private Context context;
    private ProgressDialog progressDialogue;
    private FragmentManager fragmentManager;

    public PostCardServer(Context context, String userid, String cardId, FragmentManager fragmentManager) {
        this.context = context;
        this.userid = userid;
        this.cardId = cardId;
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
    protected String doInBackground(Void... params) {
        try {
            SendMessageJsonBuilder jsonBuilder = new SendMessageJsonBuilder();
            jsonBuilder.fb_id = userid;
            jsonBuilder.id = cardId;


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
        progressDialogue.dismiss();
        Toast.makeText(context, R.string.message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, FriendListFragment.class);
        intent.putExtra("id", userid);
        context.startActivity(intent);
        //  ((fragmentCallBack) context).transactionCallBack(FragmentConstant.FRIENDLIST_FRAGMENT, userid);
        // fragmentManager.beginTransaction().replace(R.id.top_fragment_container, FriendListFragment.getInstance(userid)).commit();

    }


    public interface OnMessageSent {

        void onSendSuccess();

        void onSendFailure();
    }

    private class SendMessageJsonBuilder {

        String fb_id, id;
    }
}

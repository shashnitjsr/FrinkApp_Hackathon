package com.frink.hackathon.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frink.hackathon.R;
import com.frink.hackathon.models.FriendsList;

import java.util.ArrayList;

/**
 * Created by amandeepsingh on 17/10/15.
 */
public class FriendListAdapter extends BaseAdapter {

    private ArrayList<FriendsList.FriendName> list;
    private Context context;
    private LayoutInflater inflater;

    public FriendListAdapter(ArrayList<FriendsList.FriendName> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(ArrayList<FriendsList.FriendName> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.friends_list_view_seed, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.text.setText(list.get(position).getName());
        return convertView;
    }

    public class Holder {
        TextView text;

        Holder(View convertView) {
            text = (TextView) convertView.findViewById(R.id.text);
        }

    }
}

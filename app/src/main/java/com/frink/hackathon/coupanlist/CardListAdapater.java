package com.frink.hackathon.coupanlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.frink.hackathon.R;

import java.util.List;

/**
 * Created by shashwatsinha on 16/10/15.
 */
public class CardListAdapater extends ArrayAdapter<CardListModel.CardModel> {

    List<CardListModel.CardModel> list;
    private Context context;
    int[] drawables = new int[]{R.drawable.icon_one, R.drawable.icon_two, R.drawable.icon_three, R.drawable.icon_four};

    public CardListAdapater(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public CardListAdapater(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    public CardListAdapater(Context context, int resource, CardListModel.CardModel[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    public CardListAdapater(Context context, int resource, int textViewResourceId, CardListModel.CardModel[] objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
    }

    public CardListAdapater(Context context, int resource, List<CardListModel.CardModel> objects) {
        super(context, resource, objects);
        list = objects;
        this.context = context;

    }

    public CardListAdapater(Context context, int resource, int textViewResourceId, List<CardListModel.CardModel> objects) {
        super(context, resource, textViewResourceId, objects);
        list = objects;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = (View) layoutInflater.inflate(R.layout.card_list_row, parent, false);
        }

        ImageView personImage = (ImageView) view.findViewById(R.id.person_image);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(list.get(position).getNamePerson());
        Log.v("Image Url ", list.get(position).getPersonImage());
        //Picasso.with(context).load(list.get(position).getPersonImage()).into(personImage);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        int index = position % 4;
        imageView.setImageDrawable(getContext().getResources().getDrawable(drawables[index]));
        TextView textView = (TextView) view.findViewById(R.id.detail_coupan_text);
        Log.d("shashwat", "Coupan title " + list.get(position).getTitle());
        textView.setText(list.get(position).getTitle());

        TextView textView1 = (TextView) view.findViewById(R.id.detail_coupan_date);
        textView1.setText(list.get(position).getExpiry());
        Log.d("shashwat", "Coupan date " + list.get(position).getExpiry());
        return view;

    }
}

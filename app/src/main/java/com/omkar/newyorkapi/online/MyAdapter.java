package com.omkar.newyorkapi.online;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.omkar.newyorkapi.apis.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by omkardokur on 12/18/15.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<HashMap<String,String>> data = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;

    public MyAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        this.data = data;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieHolder movieHolder = null;
        View row = convertView;
        if(row == null){
            row = layoutInflater.inflate(R.layout.single_row,parent,false);
            movieHolder = new MovieHolder(row);
            row.setTag(movieHolder);
        }
        else {
            movieHolder = (MovieHolder)row.getTag();

        }

        HashMap<String,String> currentItem = data.get(position);

        Picasso.with(context).load(currentItem.get("src")).resize(100,100).into(movieHolder.movieImage);
        movieHolder.movieTitle.setText(currentItem.get("display_title"));
        movieHolder.movieRating.setText(currentItem.get("mpaa_rating"));
        movieHolder.movieDescription.setText(currentItem.get("summary_short"));
        return row;
    }
}

package com.omkar.newyorkapi.offline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.omkar.newyorkapi.apis.R;
import com.omkar.newyorkapi.online.MovieHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by omkardokur on 12/21/15.
 */
public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> movieData;
    LayoutInflater layoutInflater;

    public MovieAdapter(Context mContext, ArrayList<Movie> movieData) {
        this.movieData = movieData;
        this.mContext = mContext;
        this.layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movieData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieHolder movieHolder = null;
        View row = convertView;
        if(row == null){
            row = layoutInflater.inflate(R.layout.single_row_offline,parent,false);
            movieHolder = new MovieHolder(row);
            row.setTag(movieHolder);
        }
        else {
            movieHolder = (MovieHolder)row.getTag();

        }

        Picasso.with(mContext).load(R.drawable.nointernet).resize(75,75).into(movieHolder.movieImage);
        movieHolder.movieTitle.setText(movieData.get(position).getMovieName());
        movieHolder.movieRating.setText(movieData.get(position).getMovieRating());
        movieHolder.movieDescription.setText(movieData.get(position).getMovieDescription());
        return row;

    }
}

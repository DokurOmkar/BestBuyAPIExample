package com.omkar.newyorkapi.online;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omkar.newyorkapi.apis.R;

/**
 * Created by omkardokur on 12/18/15.
 */
public class MovieHolder {

    public ImageView movieImage;
    public TextView movieTitle;
    public TextView movieRating;
    public TextView movieDescription;


    public MovieHolder(View view) {
        movieImage = (ImageView)view.findViewById(R.id.imageView);
        movieTitle = (TextView) view.findViewById(R.id.textView);
        movieRating = (TextView) view.findViewById(R.id.textView2);
        movieDescription =(TextView)view.findViewById(R.id.textView3);

    }
}

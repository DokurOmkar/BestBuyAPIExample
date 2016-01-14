package com.omkar.newyorkapi.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.omkar.newyorkapi.apis.R;
import com.omkar.newyorkapi.offline.Movie;
import com.omkar.newyorkapi.offline.MovieAdapter;
import com.omkar.newyorkapi.offline.SqliteHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ResultsCallback {

    ListView listView;
    ArrayList<Movie> movieList;
    private String newyorkUrl = "http://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=ae2e42965c987fc67d9b93c63681bcfe%3A6%3A73774230";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);

        // Checks for Wi-Fi and Mobile Data connection
        ConnectionDetector connectionDetector = new ConnectionDetector(this);
        if(connectionDetector.isOnline()){
            serverConnection();
        }else{
            Log.e("There is no network", "WIFI");
            databaseConnection();
        }
    }

    public void databaseConnection() {
        movieList = (new SqliteHelper(getApplicationContext())).getMovieDetails();
        MovieAdapter adapter=new MovieAdapter(MainActivity.this,movieList);
        listView.setAdapter(adapter);
    }

    private void serverConnection() {
        DownloadAsyncTask downloadAsyc=new DownloadAsyncTask(this,MainActivity.this,ServiceHandler.GET,null);
        downloadAsyc.execute(newyorkUrl);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(ArrayList<HashMap<String, String>> results) {
        //Log.e("re", results.toString());
        listView.setAdapter(new MyAdapter(this,results));

    }
}


package com.omkar.newyorkapi.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by omkardokur on 12/21/15.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "newyorktimes";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "MOVIETABLE";
    private static final String UID = "_id";
    private static final String MOVIE_NAME = "title";
    private static final String MOVIE_RATING = "rating";
    private static final String MOVIE_DESCRIPTION = "description";
    private static final String IMAGE_DATA = "imagedata";
    private Context context;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+MOVIE_NAME+" VARCHAR(255), "+MOVIE_RATING+" VARCHAR(255), "+MOVIE_DESCRIPTION+" VARCHAR(255), "+IMAGE_DATA+" BLOB);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
            //DROP TABLE MOVIETABLE IF EXISTS;


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE MOVIETABLE (_id INTEGER PRIMARY AUTOINCREMENT, title VARCHAR(25), rating VARCHAR(25), description VARCHAR(255), imagedata BLOB);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


   public void createMovieDetails(Movie movie){
       //String deleteQuery = "DELETE * FROM " + TABLE_NAME;
       SQLiteDatabase db=this.getWritableDatabase();

       ContentValues values =new ContentValues();
       values.put(MOVIE_NAME, movie.getMovieName());
       values.put(MOVIE_RATING, movie.getMovieRating());
       values.put(MOVIE_DESCRIPTION, movie.getMovieDescription());
       values.put(IMAGE_DATA, movie.getImageURL());
       db.insert(TABLE_NAME, null, values);
   }


    //reading the  movie details
    public ArrayList<Movie> getMovieDetails()
    {ArrayList<Movie> list=new ArrayList<Movie>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setMovieId(cursor.getString(0));
                movie.setMovieName(cursor.getString(1));
                movie.setMovieRating(cursor.getString(2));
                movie.setMovieDescription(cursor.getString(3));
                // Adding movie details to list
                list.add(movie);
            } while (cursor.moveToNext());
        }
        // return movie list
        return list;
    }


}

package com.omkar.newyorkapi.online;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by omkardokur on 12/18/15.
 */
public interface ResultsCallback {
    public void onPreExecute();
    public void onPostExecute(ArrayList<HashMap<String, String>> results);
}
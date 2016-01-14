package com.omkar.newyorkapi.online;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by omkardokur on 12/21/15.
 */
public class ConnectionDetector {
    private Context mContext;

    public ConnectionDetector(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}

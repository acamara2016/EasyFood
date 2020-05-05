package com.project.easyfood_1_0.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.project.easyfood_1_0.implementations.DatabaseRestaurantRetriever;
import com.project.easyfood_1_0.implementations.DummyRestaurantRetriever;

public class RestaurantRetrieverFactory {
    private static final long FIVE_MINUTES_MILLIS = 300000;

    public static RestaurantRetriever getInstance(Context context) {
        return new DummyRestaurantRetriever();
    }


    private static boolean isConnected(Context context) {
        boolean connected = false;
        //ensure we are connected to the internet
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }

}

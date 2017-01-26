package com.app.jeferson.filmez.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.View;

import com.app.jeferson.filmez.R;

public class ConnectionChecker
{
    private static boolean isConnected(Context context) throws InterruptedException
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting() && activeNetwork.isAvailable());
    }

    public static boolean checkConnection(final Activity activity){
        try {
            if(isConnected(activity)){
                return true;
            }else{
                if(activity != null) {
                    View view =  activity.findViewById(android.R.id.content);
                    if(view != null) {
                        android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar
                                .make(view, activity.getString(R.string.connection_error), android.support.design.widget.Snackbar.LENGTH_LONG)
                                .setAction("Conectar", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        activity.startActivity(intent);
                                    }
                                });

                        snackbar.show();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


}

package com.app.jeferson.filmez.util;

import android.app.Activity;
import android.view.View;

/**
 * Created by Jeferson on 30/07/2016.
 */
public class Snackbar {
    public static void make(View view, String message){
        try {
            if(view != null && message != null && !message.isEmpty()){
                android.support.design.widget.Snackbar.make(view, message, android.support.design.widget.Snackbar.LENGTH_LONG).show();

            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Activity activity, String message){
        try {
            if(activity != null && activity.findViewById(android.R.id.content) != null && message != null && !message.isEmpty()){
                android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Activity activity, int msg){
        try {
            if(activity != null && activity.findViewById(android.R.id.content) != null){
                String message = activity.getResources().getString(msg);
                android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    public static void make(View view,  int msg){
        try {
            if(view != null){
                String message = view.getResources().getString(msg);
                android.support.design.widget.Snackbar.make(view, message, android.support.design.widget.Snackbar.LENGTH_LONG).show();

            }
        }catch(Exception e){
            e.getMessage();
        }
    }


}

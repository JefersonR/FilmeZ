package com.app.jeferson.filmez.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.app.jeferson.filmez.network.connectionService.ErrorResponse;

import okhttp3.ResponseBody;

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
    public static void make(Context context, ResponseBody response){
        try {
            String message = "";
            if(response != null){
                ErrorResponse error = ErrorResponse.getResponseError(response, 0);
                message =  error.getMessage();
            }else{
                message = "Desculpe-nos, não foi possível processar a sua solicitação no momento.";
            }

            if(context != null && ((Activity)context).findViewById(android.R.id.content) != null && message != null ){
                android.support.design.widget.Snackbar.make(((Activity)context).findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Context context, ErrorResponse response){
        try {
            String message = "";
            if(response != null){
                message =  response.getMessage();
            }else{
                message = "Desculpe-nos, não foi possível processar a sua solicitação no momento.";
            }

            if(context != null && ((Activity)context).findViewById(android.R.id.content) != null && message != null ){
                android.support.design.widget.Snackbar.make(((Activity)context).findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void make(Context context, String message){
        try {
            Activity activity = (Activity) context;
            if(activity != null && activity.findViewById(android.R.id.content) != null && message != null ){
                android.support.design.widget.Snackbar.make(activity.findViewById(android.R.id.content), message, android.support.design.widget.Snackbar.LENGTH_LONG).show();
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

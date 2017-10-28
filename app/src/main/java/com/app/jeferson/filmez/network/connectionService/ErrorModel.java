package com.app.jeferson.filmez.network.connectionService;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jeferson.filmez.util.Log;

/**
 * Created by Jeferson on 28/04/2016.
 */
public class ErrorModel {
    private String error;
    private Integer code;
    private String status;
    private String message;
    private final String fail = "Houve uma falha nos nossos servidores, tente novamente.";

    public ErrorModel(){
    }

    public ErrorModel(String error) {
        this.error = error;
        convert(error);
    }
    public String errorMessage(String error){
        convert(error);
        return getMessage();
    }

    public String errorChanges(String error){
        convert2(error);
        return getMessage();
    }
    public void convert(String error){
        try {
            if(error != null && !error.isEmpty()) {
                JSONObject obj = new JSONObject(error);
                setStatus(obj.getString("status"));
                setMessage(obj.getString("message"));
                setCode(obj.getInt("code"));
                Log.e("ERROR", getMessage() + " " + getStatus() + " "+  getCode());
            }else{
                Log.e("ERROR", "VAZIO");

                setStatus("");
                setMessage(fail);
                setCode(0);
            }

        } catch (JSONException e) {
            Log.e("ERROR", "CODE" + error);
            Log.e("ERROR CATCH", e.getMessage());
            setStatus("");
            setMessage(fail);
            setCode(0);
            e.printStackTrace();
        }
    }

    public void convert2(String error){
        try {
            if(error != null && !error.isEmpty()) {
                JSONObject obj = new JSONObject(error);
                setStatus(obj.getString("status"));
                setMessage(obj.getString("data"));
                setCode(obj.getInt("code"));
                Log.e("ERROR", getMessage() + " " + getStatus() + " "+  getCode());
            }else{
                Log.e("ERROR", "VAZIO");

                setStatus("");
                setMessage(fail);
                setCode(0);
            }

        } catch (JSONException e) {
            Log.e("ERROR", "CODE" + error);
            Log.e("ERROR CATCH", e.getMessage());
            setStatus("");
            setMessage(fail);
            setCode(0);
            e.printStackTrace();
        }
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

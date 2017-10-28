package com.app.jeferson.filmez.network.connectionService.Interfaces;

import retrofit2.Response;

/**
 * Created by Jeferson on 01/05/2017.
 */

public interface OnSucess<T> {
    public void onSucessResponse(Response<T> response);
}

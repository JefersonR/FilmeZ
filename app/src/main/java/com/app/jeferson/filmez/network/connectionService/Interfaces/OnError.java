package com.app.jeferson.filmez.network.connectionService.Interfaces;

import com.app.jeferson.filmez.network.connectionService.ErrorResponse;

/**
 * Created by Jeferson on 01/05/2017.
 */

public interface OnError<T> {
    public void onErrorResponse(ErrorResponse response);
}

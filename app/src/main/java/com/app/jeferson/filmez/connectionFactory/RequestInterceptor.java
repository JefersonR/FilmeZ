package com.app.jeferson.filmez.connectionFactory;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * Created by jeferson on 28/11/15.
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

                Request newRequest = originalRequest.newBuilder()
                .header("auth-username", "wsuser")
                .header("auth-password", "1234@livo")
                        .method(originalRequest.method(), originalRequest.body())
                        .build();

        return chain.proceed(newRequest);
    }
}
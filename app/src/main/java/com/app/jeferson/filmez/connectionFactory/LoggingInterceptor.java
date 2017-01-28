package com.app.jeferson.filmez.connectionFactory;


import com.app.jeferson.filmez.util.Log;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okio.Buffer;

/**
 * Created by jeferson on 22/11/15.
 */
public class LoggingInterceptor implements Interceptor {
    public boolean control = true;

    @Override
    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        long t1 = System.nanoTime();
        String requestLog = String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers());
        if (request.method().compareToIgnoreCase("post") == 0 || request.method().compareToIgnoreCase("put") == 0) {
            requestLog = "\n" + requestLog + "\n" + bodyToString(request);
        }
        com.squareup.okhttp.Response response = chain.proceed(request);
        String bodyString = response.body().string();
        long t2 = System.nanoTime();

        String responseLog = String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers());
        if (control) {
            control = false;
            Log.i("REQUEST", "----------------------------------------------------");
            Log.i("TAG", "REQUEST: " + requestLog);
            Log.i("TAG", "RESPONSE: " + bodyString);
            Log.i("REQUEST", "----------------------------------------------------");

        }
        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();

    }
    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
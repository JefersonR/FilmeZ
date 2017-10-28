package com.app.jeferson.filmez.network.connectionService;

import com.app.jeferson.filmez.BuildConfig;
import com.app.jeferson.filmez.util.Constants;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class NetworkSetup implements Constants {
    public static final String AUTHORIZATION = "Authorization";


    public static final OkHttpClient getClient() {

        return new OkHttpClient()
                .newBuilder()
                .dispatcher(buildDispatcher())
                .addInterceptor(getLoggingCapableHttpClient())
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();
    }

    public static final OkHttpClient getClient3() {

        return new OkHttpClient()
                .newBuilder()
                .dispatcher(buildDispatcher())
                .addInterceptor(getLoggingCapableHttpClient())
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .sslSocketFactory(getSSLSocketFactory())
                .build();
    }

/*    public static final OkHttpClient getClient2(final Context context) {

        return new OkHttpClient()
                .newBuilder()
                .dispatcher(buildDispatcher())
                .addInterceptor(getLoggingCapableHttpClient())
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .sslSocketFactory(getSSLSocketFactory())
                .addInterceptor(new Interceptor() {
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder();
                        SessionManager session = new SessionManager(context);
                        requestBuilder.addHeader(AUTHORIZATION, session.getString(token) );
                        Log.i(AUTHORIZATION,  session.getString(token));
                        final Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();

    }*/



    public static SSLSocketFactory getSSLSocketFactory() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            } };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();



            return sslSocketFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static Dispatcher buildDispatcher() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(20);
        return dispatcher;
    }

    private static HttpLoggingInterceptor getLoggingCapableHttpClient() {
        HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(BuildConfig.LOG ? HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);

        return mLogging;
    }


}

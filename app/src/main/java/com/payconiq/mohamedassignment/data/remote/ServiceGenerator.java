package com.payconiq.mohamedassignment.data.remote;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

@Singleton
public class ServiceGenerator {

    //Network constants
    private final int TIMEOUT_CONNECT = 30;   //In seconds
    private final int TIMEOUT_READ = 30;   //In seconds
    private final String CONTENT_TYPE = "Content-Type";
    private final String CONTENT_TYPE_VALUE = "application/json";

    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;
    private Gson gson;

    @Inject
    public ServiceGenerator(Gson gson) {
        this.okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(headerInterceptor);
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS);
        this.gson = gson;
    }

    public <S> S createService(Class<S> serviceClass, String baseUrl) {
        OkHttpClient client = okHttpBuilder.build();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory
                .create(gson)).build();
        return retrofit.create(serviceClass);
    }

    Interceptor headerInterceptor = chain -> {
        Request original = chain.request();

        Request request = original.newBuilder().header(CONTENT_TYPE, CONTENT_TYPE_VALUE).method(original.method(),
                original.body()).build();

        return chain.proceed(request);
    };
}
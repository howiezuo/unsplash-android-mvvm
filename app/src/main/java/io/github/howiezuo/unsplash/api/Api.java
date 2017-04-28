package io.github.howiezuo.unsplash.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.github.howiezuo.unsplash.AppApplication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String BASE_URL = "https://api.unsplash.com";

    private static Retrofit RETROFIT;

    private static Interceptor createInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .header("Authorization", "Client-ID " + AppApplication.getInstance().getClientId())
                        .build();
                return chain.proceed(request);
            }
        };
    }

    private static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(createInterceptor())
                .build();
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .create();
    }

    public static Retrofit getRetrofit() {
        if (RETROFIT == null) {
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(createHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return RETROFIT;
    }
}

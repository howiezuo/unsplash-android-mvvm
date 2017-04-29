package io.github.howiezuo.unsplash.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.app.AppApplication;
import io.github.howiezuo.unsplash.app.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
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

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient httpClient, Gson gson, RxJava2CallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Provides
    @Singleton
    PhotosService providePhotosService(Retrofit retrofit) {
        return retrofit.create(PhotosService.class);
    }
}

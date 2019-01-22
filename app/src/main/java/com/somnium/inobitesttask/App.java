package com.somnium.inobitesttask;

import android.app.Application;

import com.somnium.inobitesttask.api.PlaceHolderApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static PlaceHolderApi placeHolderApi;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        placeHolderApi = createPlaceHolderApi(interceptor);
    }

    private PlaceHolderApi createPlaceHolderApi(HttpLoggingInterceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(PlaceHolderApi.class);
    }

    public static PlaceHolderApi getPlaceHolderApi() {
        return placeHolderApi;
    }
}

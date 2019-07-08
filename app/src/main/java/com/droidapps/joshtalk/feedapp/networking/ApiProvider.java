package com.droidapps.joshtalk.feedapp.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By Rahul Vaghela on 9/7/19
 */

public class ApiProvider {

    public static final String PAGE_1 = "59b3f0b0100000e30b236b7e";
    public static final String PAGE_2 = "59ac28a9100000ce0bf9c236";
    public static final String PAGE_3 = "59ac293b100000d60bf9c239";

    private static final String BASE_URL = "http://www.mocky.io/v2/";

    private static final Object LOCK = new Object();
    private static RetrofitService sInstance;

    public static RetrofitService getApiService() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = getRetrofit().create(RetrofitService.class);
                }
            }
        }
        return sInstance;
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

}

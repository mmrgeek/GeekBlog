package com.geek.geekblog.WebService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private API api;
    private static Server instance;

    public Server(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URLs.MAIN_URL).build();
        api = retrofit.create(API.class);
    }

    public static Server getInstance(){
        if (instance == null)
            instance = new Server();
        return instance;
    }

    public API getApi() {
        return api;
    }
}

package org.example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.interceptors.BaseHeaderInterceptor;
import org.example.services.ListsService;
import org.example.services.SpacesService;
import org.example.services.TeamsService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class ClickUpApiClient {
    public TeamsService teamsService;
    public SpacesService spacesService;

    public ListsService listsService;

    public ClickUpApiClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new BaseHeaderInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.clickup.com/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        teamsService = retrofit.create(TeamsService.class);
        spacesService = retrofit.create(SpacesService.class);
        listsService = retrofit.create(ListsService.class);
    }
}

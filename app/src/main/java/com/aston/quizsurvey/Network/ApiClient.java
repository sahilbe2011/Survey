package com.aston.quizsurvey.Network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String ROOT_URL = "https://survey.astonsof.com/api/insert_survey_data.php/";
    private static ApiClient mIntanse;
    private Retrofit retrofit;
    private ApiClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public static synchronized ApiClient getInstance() {
        if (mIntanse == null) {
            mIntanse = new ApiClient();
        }
        return mIntanse;
    }
    public UserService getApi() {
        return retrofit.create(UserService.class);
    }

}

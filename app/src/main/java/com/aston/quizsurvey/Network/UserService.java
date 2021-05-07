package com.aston.quizsurvey.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;




    public interface UserService {


        @FormUrlEncoded
        @POST(".")
        Call<Loginresponce> getlogin(@Query("method") String method,
                                     @Field("phone") String phone

        );
        @FormUrlEncoded
        @POST(".")
        Call<Loginresponce> appSaveData(@Query("method") String method,
                                     @Field("survey_data") String surveyData, @Field("agent_id") String agentId,
                                        @Field("name") String name,  @Field("number") String number,
                                        @Field("village") String village
        );
    }


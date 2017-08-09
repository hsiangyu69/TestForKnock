package com.hsiangyu.testforknock.api;

import com.hsiangyu.testforknock.api.request.LoginRq;
import com.hsiangyu.testforknock.api.response.LoginRs;
import com.hsiangyu.testforknock.api.response.MemberRs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by hsiangyuchen on 07/08/2017.
 */

public interface KnockService {

    @POST("/")
    Call<LoginRs> login(@Body LoginRq loginRq);

    @GET("/member")
    Call<MemberRs> member(@Header("Authorization") String token);
}

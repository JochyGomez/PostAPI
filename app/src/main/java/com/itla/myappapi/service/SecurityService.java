package com.itla.myappapi.service;

import com.itla.myappapi.RegisterActivity;
import com.itla.myappapi.entity.Login;
import com.itla.myappapi.entity.Logout;
import com.itla.myappapi.entity.Register;
import com.itla.myappapi.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface SecurityService {

    @POST("login")
    Call<User> login(@Body Login login);

    @POST("register")
    Call<User> register(@Body Register register);

    @DELETE("logout")
    Call<Void> logout(@Header("Authorization") String token);

}

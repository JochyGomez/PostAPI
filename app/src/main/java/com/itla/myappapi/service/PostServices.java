package com.itla.myappapi.service;

import com.itla.myappapi.entity.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PostServices {

    @GET("post")
    Call<ArrayList<Post>> post(@Header("Authorization") String token);
}

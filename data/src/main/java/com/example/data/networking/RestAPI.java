package com.example.data.networking;

import com.example.domain.model.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface RestAPI {

    @POST("/posts")
    Call<Comment> addComment(@Body Comment comment);
}

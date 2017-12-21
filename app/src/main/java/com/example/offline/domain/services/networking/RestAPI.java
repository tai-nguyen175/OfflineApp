package com.example.offline.domain.services.networking;

import com.example.offline.domain.model.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface RestAPI {

    @POST("comments")
    Call<Comment> addComment(@Body Comment comment);
}

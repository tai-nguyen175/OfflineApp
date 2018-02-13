package com.example.data.networking;

import com.example.domain.model.Comment;
import com.example.mapper.CommentMapper;
import com.example.model.exception.ResponseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public final class RestAPIFactory {

    private static RestAPIFactory instance;

    private final Retrofit retrofit;

    public RestAPIFactory( ) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RestAPIFactory getInstance() {
        if (instance == null) {
            instance = new RestAPIFactory();
        }
        return instance;
    }

    public void addComment(Comment comment) throws IOException, ResponseException {
        RestAPI service = retrofit.create(RestAPI.class);

        // Remote call can be executed synchronously since the job calling it is already backgrounded.
        Response<Comment> response = service.addComment(comment).execute();

        if (response == null || !response.isSuccessful() || response.errorBody() != null) {
            throw new ResponseException(response);
        }

        Timber.d("successful remote response: " + response.body());
    }
}

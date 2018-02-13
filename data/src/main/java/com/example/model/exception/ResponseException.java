package com.example.model.exception;

import retrofit2.Response;

public class ResponseException extends Exception {

    private final Response response;

    public ResponseException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}

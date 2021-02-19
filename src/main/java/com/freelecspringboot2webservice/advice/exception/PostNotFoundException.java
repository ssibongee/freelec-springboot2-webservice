package com.freelecspringboot2webservice.advice.exception;

public class PostNotFoundException extends RuntimeException {

    private static final String POST_NOT_EXIST = "해당 게시글이 없습니다. id=";

    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(POST_NOT_EXIST + message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

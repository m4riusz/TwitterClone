package com.twitter.model;

/**
 * Created by mariusz on 18.02.16.
 */
public class ErrorResponse {
    private String url;
    private String message;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "url='" + url + '\'' +
                ", message='" + message + '\'';
    }
}

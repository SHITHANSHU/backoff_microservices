package com.walrus.serviceundererred.model;

public class ApiRequest {

    private String message;
    public ApiRequest()
    {

    }

    public ApiRequest(String message)
    {
        this.message=message;
    }

    public String getMessage()
    {
        return  this.message;
    }

}

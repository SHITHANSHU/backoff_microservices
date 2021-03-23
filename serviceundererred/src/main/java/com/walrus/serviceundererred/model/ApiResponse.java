package com.walrus.serviceundererred.model;

public class ApiResponse {

    private String message;
    private String statusCode;
    private String nextStep;

    public ApiResponse(String message, String statusCode, String nextStep) {
        this.message = message;
        this.statusCode = statusCode;
        this.nextStep = nextStep;
    }

    public ApiResponse()
    {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }
}

package ru.bellintegrator.practice.dto;

public class ErrorResponse implements ApiResponse{
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

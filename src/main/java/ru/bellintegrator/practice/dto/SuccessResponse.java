package ru.bellintegrator.practice.dto;

public class SuccessResponse {
    private String result;

    public SuccessResponse() {
        this.result = "Success";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

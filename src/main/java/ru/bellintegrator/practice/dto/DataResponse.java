package ru.bellintegrator.practice.dto;

public class DataResponse implements ApiResponse{
    private Object data;

    public DataResponse(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

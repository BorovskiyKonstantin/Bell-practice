package ru.bellintegrator.practice.dto.document;

public class DocumentDtoOut {
    private String name;
    private String code;

    public DocumentDtoOut(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}

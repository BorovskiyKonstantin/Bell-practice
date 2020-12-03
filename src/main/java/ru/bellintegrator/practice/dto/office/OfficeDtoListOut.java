package ru.bellintegrator.practice.dto.office;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficeDtoListOut {
    private Integer id;
    private String name;
    @JsonProperty("isActive")
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

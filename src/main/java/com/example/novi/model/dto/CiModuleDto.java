package com.example.novi.model.dto;

public class CiModuleDto {
    private Long id;

    private String name;
    private String type;

    private double price;

    private Long televisionDtoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getTelevisionDtoId() {
        return televisionDtoId;
    }

    public void setTelevisionDtoId(Long televisionDtoId) {
        this.televisionDtoId = televisionDtoId;
    }
}

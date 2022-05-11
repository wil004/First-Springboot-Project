package com.example.novi.model.dto;

import java.util.List;

public class WallBracketDto {
    private Long id;
    private String size;
    private String name;

    private double price;

    private boolean adjustable;

    private List<TelevisionDto> televisionDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAdjustable() {
        return adjustable;
    }

    public void setAdjustable(boolean adjustable) {
        this.adjustable = adjustable;
    }

    public List<TelevisionDto> getTelevisionDto() {
        return televisionDto;
    }

    public void setTelevisionDto(List<TelevisionDto> televisionDto) {
        this.televisionDto = televisionDto;
    }
}

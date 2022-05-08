package com.example.novi.model.dto;

import com.example.novi.model.Television;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class RemoteDto {
    private Long id;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;

    private double price;

    private int originalStock;

    private TelevisionDto televisionDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }

    public TelevisionDto getTelevisionDto() {
        return televisionDto;
    }

    public void setTelevisionDto(TelevisionDto televisionDto) {
        this.televisionDto = televisionDto;
    }
}

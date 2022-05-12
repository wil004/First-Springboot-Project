package com.example.novi.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class WallBracket {
    @Id
    @GeneratedValue
    private Long id;

    private String size;
    private String name;

    private double price;

    private boolean adjustable;

    @OneToMany(mappedBy = "wallBracketId")
    private List<TelevisionAndWallBrackets> televisionAndWallBrackets;

    @ManyToMany
    private List<Television> televisions;


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


    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }

    public List<TelevisionAndWallBrackets> getTelevisionAndWallBrackets() {
        return televisionAndWallBrackets;
    }

    public void setTelevisionAndWallBrackets(List<TelevisionAndWallBrackets> televisionAndWallBrackets) {
        this.televisionAndWallBrackets = televisionAndWallBrackets;
    }
}

package com.example.novi.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Television {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String brand;
    @Column
    private String type;
    @Column
    private String name;

    @Column
    private double price;
    @Column
    private double availableSize;
    @Column
    private double reshRate;

    @Column
    private String screenType;
    @Column
    private String screenQuality;
    @Column
    private boolean smartTv;
    @Column
    private boolean wifi;
    @Column
    private boolean hdr;
    @Column
    private boolean bluetooth;
    @Column
    private boolean ambiLight;

    @Column
    private int originalStock;
    @Column
    private int sold;

    @OneToOne
    private Remote remote;

    @OneToMany(mappedBy = "television")
    private List<CiModule> ciModule;

    @ManyToMany
    private List<WallBracket> wallBrackets;


    public Television() {};

    public Television(String television) {
        this.brand = television;
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {return this.brand;};

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getAvailableSize() {
        return availableSize;
    }

    public void setAvailableSize(double availableSize) {
        this.availableSize = availableSize;
    }

    public double getReshRate() {
        return reshRate;
    }

    public void setReshRate(double reshRate) {
        this.reshRate = reshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public boolean isSmartTv() {
        return smartTv;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isHdr() {
        return hdr;
    }

    public void setHdr(boolean hdr) {
        this.hdr = hdr;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isAmbiLight() {
        return ambiLight;
    }

    public void setAmbiLight(boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public int getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Remote getRemote() {
        return remote;
    }

    public void setRemote(Remote remote) {
        this.remote = remote;
    }

    public List<CiModule> getCiModule() {
        return ciModule;
    }

    public void setCiModule(List<CiModule> ciModule) {
        this.ciModule = ciModule;
    }

    public List<WallBracket> getWallBrackets() {
        return wallBrackets;
    }

    public void setWallBrackets(List<WallBracket> wallBrackets) {
        this.wallBrackets = wallBrackets;
    }
}

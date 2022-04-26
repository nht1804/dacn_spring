package com.nttu.dacnsv.Model;

public class CarDetail {
    private int seat;
    private String transmission;
    private String manufacturer;
    private String about;
    private boolean hasDriver;

    public CarDetail(int seat, String transmission, String manufacturer, String about, boolean hasDriver) {
        this.seat = seat;
        this.transmission = transmission;
        this.manufacturer = manufacturer;
        this.about = about;
        this.hasDriver = hasDriver;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isHasDriver() {
        return hasDriver;
    }

    public void setHasDriver(boolean hasDriver) {
        this.hasDriver = hasDriver;
    }
}

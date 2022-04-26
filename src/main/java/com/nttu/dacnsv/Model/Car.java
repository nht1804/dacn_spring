package com.nttu.dacnsv.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Car")
public class Car {
    @Id
    private String id;
    private String name;
    private String price;
    private CarDetail detail;
    private List<String> image;
    private Boolean status;
    private int count;

    public Car(String name, String price, CarDetail detail, List<String> image, Boolean status) {
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.image = image;
        this.status = status;
        this.count = 0;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CarDetail getDetail() {
        return detail;
    }

    public void setDetail(CarDetail detail) {
        this.detail = detail;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

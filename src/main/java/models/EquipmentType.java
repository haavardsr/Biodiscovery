package models;

import java.util.ArrayList;

public class EquipmentType {
    private int id;
    private int equipmentCategoryId;
    private int specs_id;
    private String name;
    private boolean isFreeToRentForOneDay;
    private int rentPrice;
    private int maxTotalDaysForRent;
    private String img;
    private int count;

    public int getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(int equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpecs_id() {
        return specs_id;
    }

    public void setSpecs_id(int specs_id) {
        this.specs_id = specs_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFreeToRentForOneDay() {
        return isFreeToRentForOneDay;
    }

    public void setFreeToRentForOneDay(boolean freeToRentForOneDay) {
        isFreeToRentForOneDay = freeToRentForOneDay;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getMaxTotalDaysForRent() {
        return maxTotalDaysForRent;
    }

    public void setMaxTotalDaysForRent(int maxTotalDaysForRent) {
        this.maxTotalDaysForRent = maxTotalDaysForRent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

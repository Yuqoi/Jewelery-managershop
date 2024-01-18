package org.yuqoi.managerapp.models;

public class Watch {


    private int watchId;
    private String watchName;
    private String brand;
    private Gender gender;
    private String mpn;
    private MechanismType mechanismType;
    private double price;

    public Watch(int watchId, String watchName, String brand, Gender gender, String mpn, MechanismType mechanismType, double price) {
        this.watchId = watchId;
        this.watchName = watchName;
        this.brand = brand;
        this.gender = gender;
        this.mpn = mpn;
        this.mechanismType = mechanismType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Watch{" +
                "watchId=" + watchId +
                ", watchName='" + watchName + '\'' +
                ", brand='" + brand + '\'' +
                ", gender=" + gender +
                ", mpn='" + mpn + '\'' +
                ", mechanismType=" + mechanismType +
                ", price=" + price +
                '}';
    }

    public int getWatchId() {
        return watchId;
    }

    public String getWatchName() {
        return watchName;
    }

    public String getBrand() {
        return brand;
    }

    public Gender getGender() {
        return gender;
    }

    public String getMpn() {
        return mpn;
    }

    public MechanismType getMechanismType() {
        return mechanismType;
    }

    public double getPrice() {
        return price;
    }
}

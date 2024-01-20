package org.yuqoi.managerapp.models;

public class Watch {

    private int watchId;
    private String watchName;
    private String brand;
    private Gender gender;
    private String mpn;
    private MechanismType mechanismType;
    private double price;


    // instance for retrieving data
    private static Watch instance = new Watch();
    public Watch getInstance(){
        return instance;
    }



    public Watch(){}
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

    public void setWatchId(int watchId) {
        this.watchId = watchId;
    }

    public void setWatchName(String watchName) {
        this.watchName = watchName;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public void setMechanismType(MechanismType mechanismType) {
        this.mechanismType = mechanismType;
    }

    public void setPrice(double price) {
        this.price = price;
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

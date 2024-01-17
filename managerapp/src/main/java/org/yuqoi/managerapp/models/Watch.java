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


}

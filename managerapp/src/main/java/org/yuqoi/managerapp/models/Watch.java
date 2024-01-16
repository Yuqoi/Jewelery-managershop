package org.yuqoi.managerapp.models;

public class Watch {


    private int watchId;
    private String watchName;
    private String brand;
    private Sex sex;
    private String mpn;
    private MechanismType mechanismType;
    private double price;

    public Watch(int watchId, String watchName, String brand, Sex sex, String mpn, MechanismType mechanismType, double price) {
        this.watchId = watchId;
        this.watchName = watchName;
        this.brand = brand;
        this.sex = sex;
        this.mpn = mpn;
        this.mechanismType = mechanismType;
        this.price = price;
    }


}

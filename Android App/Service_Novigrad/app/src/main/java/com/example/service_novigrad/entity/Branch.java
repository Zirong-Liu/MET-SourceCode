package com.example.service_novigrad.entity;

public class Branch {

    private int branchCode;
    private String address;
    private String city;
    private String openingHours;

    public Branch(int branchCode,String address,String city,String openingHours) {
        this.branchCode = branchCode;
        this.address = address;
        this.city = city;
        this.openingHours = openingHours;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getOpeningHours() {
        return openingHours;
    }
}

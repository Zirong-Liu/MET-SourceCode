package com.example.service_novigrad.entity;

public class DriverLicense extends Service
{
    String licenseType;

    public DriverLicense(String serviceName, String licenseType) {
        super(serviceName);
        this.licenseType=licenseType;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }
}

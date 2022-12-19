package com.example.service_novigrad.entity;

public class Service
{
    String serviceName;

    public Service(String serviceName)
    {
        this.serviceName=serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

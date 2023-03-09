package com.example.localservicesfind;

public class ModelClass {
    String name, address, street, category;
    int mobile, pin;


    public ModelClass(String name, String  address, String street, int mobile, int pin, String category)
    {
        this.name = name;
        this.address = address;
        this.street = street;
        this.mobile = mobile;
        this.pin = pin;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}

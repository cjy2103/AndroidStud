package com.example.myapplication;

public class MyItem {
    String id;
    String phone;

    public MyItem(String id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public MyItem(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

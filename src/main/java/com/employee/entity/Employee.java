package com.employee.entity;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;
    private String name;
    private String email;
    private String address;
    private int phone;
    private String imageUrl;

    public Employee() {
    }

    public Employee(int eid, String name, String email, String address, int phone) {
        this.eid = eid;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Employee(int eid, String name, String email, String address, int phone, String imageUrl) {
        this.eid = eid;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

}

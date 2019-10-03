package com.managers.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullname;
    private LocalDate birthdate;
    private String address;
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;

    public Students() {
    }

    public Students(String fullname, LocalDate birthdate, String address, String avatar) {
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.address = address;
        this.avatar = avatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}

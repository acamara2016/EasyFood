package com.project.easyfood_1_0.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {
    private String name;
    private String username;
    private String email;
    private String phone_numb;
    private String address;
    private String id;
    private int opening_time;
    private int closing_time;
    private double latitude;
    private double longitude;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    private String working_hours;
    private String image;

    public String getFr_description() {
        return fr_description;
    }

    public void setFr_description(String fr_description) {
        this.fr_description = fr_description;
    }

    private String fr_description;

    public String getEn_description() {
        return en_description;
    }

    public void setEn_description(String en_description) {
        this.en_description = en_description;
    }

    private String en_description;
    public double getLatitude() {
        return latitude;
    }

    public String getImage(){return image;}
    public void setImage(String image){this.image=image;}

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public Restaurant(){

    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone_numb='" + phone_numb + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", opening_time=" + opening_time +
                ", closing_time=" + closing_time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", type='" + type + '\'' +
                ", rating='" + rating + '\'' +
                ", working_hours='" + working_hours + '\'' +
                ", image='" + image + '\'' +
                ", fr_description='" + fr_description + '\'' +
                ", en_description='" + en_description + '\'' +
                '}';
    }

    public Restaurant(String name, String image, String username, String email, String phone_numb, String address, String id, String working_hours, double longitude, double latitude, String fr_description, String en_description, String rating, String type) {
        this.name = name;
        this.image = image;
        this.username = username;
        this.email = email;
        this.phone_numb = phone_numb;
        this.address = address;
        this.id = id;
        this.working_hours = working_hours;
        this.latitude=latitude;
        this.longitude=longitude;
        this.fr_description=fr_description;
        this.en_description=en_description;
        this.rating=rating;
        this.type=type;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_numb() {
        return phone_numb;
    }

    public void setPhone_numb(String phone_numb) {
        this.phone_numb = phone_numb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(int opening_time) {
        this.opening_time = opening_time;
    }

    public int getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(int closing_time) {
        this.closing_time = closing_time;
    }



}

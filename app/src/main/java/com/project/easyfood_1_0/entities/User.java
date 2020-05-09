package com.project.easyfood_1_0.entities;

public class User {
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String phone_numb;
    private String address;
    private String id;

    public User(){

    }

    public User(String first_name, String last_name, String username, String email, String phone_numb, String address, String id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.phone_numb = phone_numb;
        this.address = address;
        this.id = id;
    }

    public String getUID() {
        return id;
    }

    public void setUID(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name){this.last_name=last_name;}
    public String getLast_name(){return last_name;}
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

    public String toString(){
        return ("ID: "+this.getUID()+" Name: "+this.getFirst_name()+" "+this.getLast_name()+" Username: "+this.getUsername()+" Email: "+this.getEmail()+" Phone number: "+this.getPhone_numb()+" Address: "+this.getAddress());
    }


}

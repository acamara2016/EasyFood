package com.project.easyfood_1_0.entities;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private int price;
    private String image;
    private String category;
    public Food(){

    }
    public Food(int id,String name, int price, String image, String category){
        this.id = id;
        this.name=name;
        this.price=price;
        this.image=image;
        this.category=category;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory(){
        return category;
    }

    public boolean isComfortFood(){
        if(this.getCategory() == "Comfort")
            return true;
        return false;
    }
    public boolean isPasta(){
        if(this.getCategory() == "Pasta")
            return true;
        return false;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString(){
        return ("FoodID: "+this.id+" FoodName: "+this.name+" Image: "+this.image+" Price: "+this.price+" Category: "+category);
    }


}

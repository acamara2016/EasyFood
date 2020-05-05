package com.project.easyfood_1_0.entities;

import java.util.ArrayList;

public class Menu {
    private int id;
    private String name;
    private ArrayList<Food> menuItem;

    public Menu(){

    }
    public Menu(int id, String name,ArrayList<Food> menuItems){
        this.id=id;
        this.name=name;
        this.menuItem=menuItems;
    }

    public void setID(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public int getID(){
        return id;
    }

    public void removeFood(Food food){
        ArrayList<Food> menuItem = this.menuItem;
        int size = menuItem.size();
        int i=0;
        for(i=0;i<size;i++){
            if(menuItem.get(i).getId()==food.getId()) {
                menuItem.remove(i);
                break;
            }
        }
    }


    public void addFood(Food food){
        menuItem.add(food);
    }


}

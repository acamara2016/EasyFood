package com.project.easyfood_1_0.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.project.easyfood_1_0.entities.Comment;
import com.project.easyfood_1_0.entities.Food;
import com.project.easyfood_1_0.entities.Restaurant;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class LocalDatabaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "event_table";
    private static final String DATABASE_NAME = "EasyFoodDB";
    private static final String RESTAURANT_NAME = "name";
    private static final String RESTAURANT_DESC_FR = "description_fr";
    private static final String RESTAURANT_DESC_EN = "description_en";
    private static final String RESTAURANT_ADDRESS = "address";
    private static final String OPENING_HOUR = "date";
    private static final String RESTAURANT_IMAGE = "image_url";
    private static final String RESTAURANT_LONG = "longitude";
    private static final String RESTAURANT_LAT = "latitude";
    private static final String FOOD_NAME = "food_name";
    private static final String FOOD_PRICE = "food_price";
    private static final String FOOD_IMAGE = "food_image";
    private static final String FOOD_CATEGORY = "food_category";


    public LocalDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY," +
                " name VARCHAR(255), " +
                "description_fr TEXT, " +
                "description_en TEXT, " +
                "address TEXT, " +
                "date TEXT, " +
                "image_url TEXT, " +
                "food_name TEXT, "+
                "food_price TEXT, "+
                "food_image TEXT, "+
                "food_category TEXT, "+
                "longitude REAL, " +
                "latitude REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESTAURANT_NAME, restaurant.getName());
        contentValues.put(RESTAURANT_DESC_FR, restaurant.getFr_description());
        contentValues.put(RESTAURANT_DESC_EN, restaurant.getEn_description());
        contentValues.put(RESTAURANT_ADDRESS, restaurant.getAddress());
        contentValues.put(OPENING_HOUR, restaurant.getOpening_time());
        contentValues.put(RESTAURANT_IMAGE, restaurant.getImage());
        contentValues.put(RESTAURANT_LAT, restaurant.getLongitude());
        contentValues.put(RESTAURANT_LAT, restaurant.getLatitude());

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public boolean insertFoodIntoMenu(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_NAME, food.getName());
        contentValues.put(FOOD_PRICE, food.getPrice());
        contentValues.put(FOOD_IMAGE, food.getImage());
        contentValues.put(FOOD_CATEGORY, food.getCategory());

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public void dropAndSetRestaurant(List<Restaurant> restaurants) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        for (Restaurant restaurant : restaurants) {
            insertRestaurant(restaurant);
        }
    }
    public void dropAndSetMenu(List<Food> menu){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
        for(Food food : menu){
            insertFoodIntoMenu(food);
        }
    }
    public List<Food> getFoods(){
        List<Food> menu = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do{
                Food food = new Food(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                menu.add(food);
            }while (cursor.moveToNext());
        }cursor.close();
        return menu;
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                Restaurant restaurant = new Restaurant(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getDouble(8),
                        cursor.getDouble(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14));
                restaurants.add(restaurant);
            }while(cursor.moveToNext());
        }cursor.close();
        return restaurants;
    }
}


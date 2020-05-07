package com.project.easyfood_1_0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.easyfood_1_0.entities.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    Context context;
    List<Food> menu;
    LayoutInflater inflter;

    public CustomListAdapter(Context applicationContext, List<Food> menu) {
        this.context = context;
        this.menu = menu;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.actiivity_listview, null);
        TextView food_name = (TextView) view.findViewById(R.id.food_name);
        TextView food_price = (TextView) view.findViewById(R.id.food_price);
        TextView food_desc = (TextView) view.findViewById(R.id.food_description);
        ImageView icon = (ImageView) view.findViewById(R.id.food_image);
        food_name.setText(menu.get(i).getName());
        //Picasso.get().load(menu.get(i).getImage()).fit().centerCrop().into(icon);
        //food_price.setText(menu.get(i).getPrice());
        //food_desc.setText(menu.get(i).getName());
        return view;
    }
}
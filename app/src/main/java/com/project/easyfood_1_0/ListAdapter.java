package com.project.easyfood_1_0;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.project.easyfood_1_0.entities.Food;
import com.project.easyfood_1_0.entities.Restaurant;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    public List<Restaurant> data = new ArrayList<>();
    public List<Food> menu = new ArrayList<>();
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView restaurant_name_View, eventDateDay, eventDateMonth, eventDescription;
        private ImageView eventImageView;
        private TextView rating_view, type_view;

        private ListViewHolder(View itemView) {
            super(itemView);
            restaurant_name_View = itemView.findViewById(R.id.event_name_view);
            eventImageView = itemView.findViewById(R.id.event_image_view);
            rating_view = itemView.findViewById(R.id.rating_view);
            type_view = itemView.findViewById(R.id.event_category);

        }

        private void bindView(int position) {
            final Restaurant restaurant = data.get(position);
            rating_view.setText(restaurant.getRating());
            if(restaurant.getType()!=null)
                type_view.setText(restaurant.getType());
            else
                type_view.setText("Null");
            restaurant_name_View.setText(restaurant.getName());
            //eventNameView.setText(event.getEventName());
            Picasso.get().load(restaurant.getImage()).fit().centerCrop().into(eventImageView);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Bundle bundle = new Bundle();
                    bundle.putSerializable("restaurant", restaurant);

                    Navigation.findNavController(itemView)
                            .navigate(R.id.action_navigation_home_to_detailFragment, bundle);
                }
            };
            itemView.setOnClickListener(listener);
        }
    }
}

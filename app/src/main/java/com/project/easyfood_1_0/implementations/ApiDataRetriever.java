package com.project.easyfood_1_0.implementations;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.project.easyfood_1_0.api.RestaurantRetriever;
import com.project.easyfood_1_0.database.LocalDatabaseHandler;
import com.project.easyfood_1_0.entities.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ApiDataRetriever implements RestaurantRetriever {

    private Context context;
    private MutableLiveData<List<Restaurant>> events;
    private FileReader reader;
    private final String EVENT_ENDPOINT = "https://afhalifax.ca/EVLOFFICE/a.php";
    public final static String LAST_DATA_RETRIEVAL_TIME = "last_data_retrieval_time";
    private LocalDatabaseHandler db;

    public ApiDataRetriever(Context context) throws FileNotFoundException {
        this.context = context;
        events = new MutableLiveData<>();
        db = new LocalDatabaseHandler(context);
        reader = new FileReader("../../../res/data.json");
    }

    @Override
    public LiveData<List<Restaurant>> getRestaurants() {
        RequestQueue reqQueue = Volley.newRequestQueue(context);
        JsonArrayRequest req = new JsonArrayRequest(EVENT_ENDPOINT,
                new EventApiResponseListener(),
                new EventApiResponseListener());
        reqQueue.add(req);
        return events;
    }

    @Override
    public LiveData<List<Restaurant>> getRestaurantsBamako() {
        return null;
    }

    class EventApiResponseListener implements Response.Listener<JSONArray>, Response.ErrorListener {
        @Override
        public void onResponse(JSONArray response) {
            List<Restaurant> list = new ArrayList<>();
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonEvent = response.getJSONObject(i);
                    double lat = 0.0, lon = 0.0;
                    String email ="blank", phone="blank";
                    if(!jsonEvent.getString("email").isEmpty())
                        email = jsonEvent.getString("email");
                    if(!jsonEvent.getString("phone").isEmpty())
                        phone = jsonEvent.getString("phone");

                    Restaurant restaurant = new Restaurant(
                            jsonEvent.getString("name"),
                            jsonEvent.getString("photo"),
                            "blank username",
                            email,
                            phone,
                            jsonEvent.getString("address"),
                            jsonEvent.getString("google_id"),
                            jsonEvent.getString("working_hours"),
                            jsonEvent.getDouble("longitude"),
                            jsonEvent.getDouble("latitude"),
                            "French description",
                            "English description",
                            jsonEvent.getString("rating"),
                            jsonEvent.getString("type"),
                            jsonEvent.getString("address_city"),
                            jsonEvent.getString("address_street")

                    );
                    list.add(restaurant);
                }
                events.setValue(list);
                if (!list.isEmpty()) {
                    db.dropAndSetRestaurant(list);
                    SharedPreferences pref = context
                            .getSharedPreferences("easyFoodSetting", Context.MODE_PRIVATE);
                    pref.edit().putLong(LAST_DATA_RETRIEVAL_TIME, System.currentTimeMillis()).apply();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    }

}


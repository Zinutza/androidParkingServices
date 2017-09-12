package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zina.parkingandroidapp.model.ParkingLocation;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.FavouritesService;

import java.util.List;

import static com.example.zina.parkingandroidapp.util.ApplicationContext.favouritesService;

public class FavouritesActivity extends AppCompatActivity {

    private FavouritesService favouritesService;

    private User user;

    private ListView favouritesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        favouritesService = favouritesService();
        List<ParkingLocation> favouriteLocations = favouritesService.list(user);

        favouritesListView = (ListView) findViewById(R.id.favouritesListView);

        ArrayAdapter<ParkingLocation> arrayAdapter = new ArrayAdapter<ParkingLocation>(
                this,
                android.R.layout.simple_list_item_1,
                favouriteLocations );

        favouritesListView.setAdapter(arrayAdapter);
    }
}

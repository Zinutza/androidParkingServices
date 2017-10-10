package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

    private ArrayAdapter<ParkingLocation> favouritesListAdapter;

    private ParkingLocation clickedFavourite;

    //TODO: handle no favourites

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        favouritesService = favouritesService();
        List<ParkingLocation> favouriteLocations = favouritesService.list(user);

        favouritesListView = (ListView) findViewById(R.id.favouritesListView);

        favouritesListAdapter = new ArrayAdapter<ParkingLocation>(
                this,
                android.R.layout.simple_list_item_1,
                favouriteLocations );

        favouritesListView.setAdapter(favouritesListAdapter);

        favouritesListView.setOnItemClickListener(new FavouritesClickHandler());
    }

    private void transitionToMapActivity() {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("focusLocation", clickedFavourite);
        startActivity(intent);
    }


    private class FavouritesClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            clickedFavourite = favouritesListAdapter.getItem(position);
            transitionToMapActivity();
        }
    }

    @Override
    public void onBackPressed() {
        transitionToMapActivity();
    }
}

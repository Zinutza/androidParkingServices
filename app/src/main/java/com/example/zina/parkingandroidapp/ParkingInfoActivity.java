package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.zina.parkingandroidapp.model.ParkingLocation;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.FavouritesService;

import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.zina.parkingandroidapp.util.ApplicationContext.favouritesService;

public class ParkingInfoActivity extends AppCompatActivity {


    TextView nameLabel;
    TextView typeLabel;

    Button addFavouriteButton;
    Button removeFavouriteButton;

    ParkingLocation parkingLocation;
    User user;
    List<ParkingLocation> favourites;

    FavouritesService favouritesService = favouritesService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);

        nameLabel = (TextView) findViewById(R.id.nameLabel);
        typeLabel = (TextView) findViewById(R.id.typeLabel);

        addFavouriteButton = (Button) findViewById(R.id.addFavouriteButton);
        addFavouriteButton.setVisibility(VISIBLE);
        removeFavouriteButton = (Button) findViewById(R.id.removeFavouriteButton);
        removeFavouriteButton.setVisibility(VISIBLE);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        parkingLocation = (ParkingLocation) intent.getSerializableExtra("parkingLocation");

        favourites = favouritesService.list(user);

        nameLabel.setText(parkingLocation.getAddress());

        if(parkingLocation.getType().isFree()) {
            typeLabel.setText("Free");
        } else {
            typeLabel.setText("Paid");
        }

        if(aFavourite(parkingLocation)) {
            addFavouriteButton.setVisibility(INVISIBLE);
        } else {
            removeFavouriteButton.setVisibility(INVISIBLE);
        }
    }

    private boolean aFavourite(ParkingLocation parkingLocation) {
        return true;
    }

}

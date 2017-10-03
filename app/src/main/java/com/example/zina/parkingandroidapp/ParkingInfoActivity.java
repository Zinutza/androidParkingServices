package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zina.parkingandroidapp.model.FavouriteLocation;
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
        addFavouriteButton.setOnClickListener(new AddButtonOnClickListener());
        removeFavouriteButton = (Button) findViewById(R.id.removeFavouriteButton);
        removeFavouriteButton.setVisibility(VISIBLE);
        removeFavouriteButton.setOnClickListener(new RemoveButtonOnClickListener());

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
        for(ParkingLocation favourite : favourites) {
            if(favourite.getId().equals(parkingLocation.getId())) {
                return true;
            }
        }
        return false;
    }

    private void transitionToFavouritesScreen() {
        Intent intent = new Intent(this, FavouritesActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void transitionToMapScreen() {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private class AddButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            favouritesService.create(new FavouriteLocation(user.getId(), parkingLocation.getId()));
            transitionToFavouritesScreen();
        }
    }

    private class RemoveButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            favouritesService.delete(new FavouriteLocation(user.getId(), parkingLocation.getId()));
            transitionToMapScreen();
        }
    }


}

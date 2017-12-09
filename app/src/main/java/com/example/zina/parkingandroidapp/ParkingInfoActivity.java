package com.example.zina.parkingandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zina.parkingandroidapp.model.FavouriteLocation;
import com.example.zina.parkingandroidapp.model.ParkingLocation;
import com.example.zina.parkingandroidapp.model.User;
import com.example.zina.parkingandroidapp.services.FavouritesService;
import com.example.zina.parkingandroidapp.services.ParkingLocationServices;
import com.example.zina.parkingandroidapp.services.RatingsService;
import com.example.zina.parkingandroidapp.util.ApplicationContext;

import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.zina.parkingandroidapp.util.ApplicationContext.favouritesService;

public class ParkingInfoActivity extends AppCompatActivity {


    TextView nameLabel;
    TextView typeLabel;
    TextView tv_average;

    Button addFavouriteButton;
    Button removeFavouriteButton;
    Button deleteLocationButton;

    ParkingLocation parkingLocation;
    User user;
    List<ParkingLocation> favourites;

    FavouritesService favouritesService = favouritesService();
    RatingsService ratingsService = ApplicationContext.ratingsService();
    ParkingLocationServices parkingLocationServices = ApplicationContext.parkingLocationServices();

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);

        nameLabel = (TextView) findViewById(R.id.nameLabel);
        typeLabel = (TextView) findViewById(R.id.typeLabel);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tv_average = (TextView) findViewById(R.id.average);

        addFavouriteButton = (Button) findViewById(R.id.addFavouriteButton);
        addFavouriteButton.setVisibility(VISIBLE);
        addFavouriteButton.setOnClickListener(new AddButtonOnClickListener());
        removeFavouriteButton = (Button) findViewById(R.id.removeFavouriteButton);
        removeFavouriteButton.setVisibility(VISIBLE);
        removeFavouriteButton.setOnClickListener(new RemoveButtonOnClickListener());

        deleteLocationButton = (Button) findViewById(R.id.delete_location);
        deleteLocationButton.setOnClickListener(new DeleteLocationButtonOnClickListener());

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        parkingLocation = (ParkingLocation) intent.getSerializableExtra("parkingLocation");

        displayDeleteButton(user, parkingLocation);

        favourites = favouritesService.list(user);

        nameLabel.setText(parkingLocation.getAddress());

        setupRatingBar(user, parkingLocation);

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

    private void displayDeleteButton(User user, ParkingLocation parkingLocation) {
        if(user.getId().equals(parkingLocation.getCreatorId())) {
            deleteLocationButton.setVisibility(VISIBLE);
        }
    }

    private void setupRatingBar(final User user, final ParkingLocation parkingLocation) {
        final Long userRating = ratingsService.readUserRating(parkingLocation.getId(), user.getId());
        Float average = ratingsService.readAverage(parkingLocation.getId());

        if(userRating != 0) {
            ratingBar.setRating(userRating);
        }
        tv_average.setText("Average " + average + " stars");



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingsService.createOrUpdate(parkingLocation.getId(), user.getId(), v);
            }
        });
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

    private class DeleteLocationButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            parkingLocationServices.delete(parkingLocation.getId());
            transitionToMapScreen();
        }
    }
}

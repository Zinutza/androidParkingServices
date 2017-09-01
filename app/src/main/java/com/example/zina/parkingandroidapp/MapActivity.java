package com.example.zina.parkingandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.zina.parkingandroidapp.location.GPSTracker;
import com.example.zina.parkingandroidapp.model.ParkingLocation;
import com.example.zina.parkingandroidapp.model.ParkingType;
import com.example.zina.parkingandroidapp.services.ParkingLocationServices;
import com.example.zina.parkingandroidapp.util.ApplicationContext;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.zina.parkingandroidapp.model.ParkingType.FREE;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Geocoder geocoder;

    private ParkingLocationServices parkingLocationServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == -1) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }

        geocoder = new Geocoder(this, Locale.getDefault());
        parkingLocationServices = ApplicationContext.parkingLocationServices();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Get Current location
        GPSTracker tracker = new GPSTracker(getApplicationContext());
        Location location = tracker.getLocation();
        int locationCounter = 0;
        while(location == null) {
            locationCounter++;
            location = tracker.getLocation();
            if(locationCounter == 100) {
                throw new IllegalStateException("Unable to find current location");
            }
        }


        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));


        List<ParkingLocation> nearbyParking = parkingLocationServices
                .findParkingNearby(currentLocation.latitude, currentLocation.longitude);


        for (ParkingLocation nearbyLocation : nearbyParking) {
            addParkingLocationToMap(nearbyLocation);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12f));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng point) {

                // Create dropdown items
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View dialogLayout =  li.inflate(R.layout.dialog_new_location, null);
                final Spinner parkingType = dialogLayout.findViewById(R.id.parking_type_spinner);
                String[] parkingTypes = new String[]{"Free", "Paid"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, parkingTypes);
                parkingType.setAdapter(adapter);

                final EditText etAddress = dialogLayout.findViewById(R.id.etAddress);

                AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                builder.setMessage("Would you like to add a new parking location?").setTitle("New Location");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ParkingLocation newParkingLocation = new ParkingLocation();
                        newParkingLocation.setAddress(etAddress.getText().toString());
                        newParkingLocation.setType(ParkingType.valueOf(parkingType.getSelectedItem().toString().toUpperCase()));
                        newParkingLocation.setLatitude(point.latitude);
                        newParkingLocation.setLongitude(point.longitude);
                        ParkingLocation createdParkingLocation = parkingLocationServices.createParkingLocation(newParkingLocation);
                        addParkingLocationToMap(createdParkingLocation);
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();

                dialog.setView(dialogLayout);
                dialog.show();
            }
        });
    }

    private void addParkingLocationToMap(ParkingLocation parkingLocation) {
        LatLng markerLocation = new LatLng(parkingLocation.getLatitude(), parkingLocation.getLongitude());
        MarkerOptions marker = new MarkerOptions();
        marker.position(markerLocation);

        if (parkingLocation.getAddress() != null) {
            marker.title(parkingLocation.getAddress());
        } else {
            try {
                List<Address> addressList = geocoder.getFromLocation(markerLocation.latitude, markerLocation.longitude, 1);
                marker.title(addressList.get(0).getAddressLine(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (parkingLocation.getType() == FREE) {
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        } else {
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        }
        mMap.addMarker(marker);
    }
}

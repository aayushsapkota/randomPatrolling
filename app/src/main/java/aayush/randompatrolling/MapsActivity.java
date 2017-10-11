package aayush.randompatrolling;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.NETWORK_PROVIDER;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private ArrayList<SelectedLocation> selectedLocations = new ArrayList<>();
    SelectedLocation selectedLocationObj = new SelectedLocation();
    double latitude;
    double longitude;
    double placeCheckBackTime;
    double minStay;
    double maxStay;
    double priorityIndex;
    String addressname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                centerMapLocation(location, "Your location");
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 300, 500, locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                centerMapLocation(lastLocation, "Your location");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }


        Button places = (Button) findViewById(R.id.places);
        Button alarms = (Button) findViewById(R.id.alarms);

        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), places.class);
                startActivity(i);
            }
        });

        alarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), alarms.class);
                startActivity(i);
            }
        });

        Intent getPlaces = getIntent();
        if (getPlaces.getStringExtra("addressName") != null) {
            addressname = getPlaces.getStringExtra("addressName");
            Log.d("addressName", addressname);
        }

        if (getPlaces.getDoubleExtra("latitude", 0) != 0) {
            latitude = getPlaces.getDoubleExtra("latitude", 0);
            Log.d("latitude", String.valueOf(latitude));
        }
        if (getPlaces.getDoubleExtra("longitude", 0) != 0) {
            longitude = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("minTimeStay", 0) != 0) {
            longitude = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("maxTimeStay", 0) != 0) {
            longitude = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("priorityIndex", 0) != 0) {
            longitude = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("latitude", 0) != 0) {
            latitude = getPlaces.getDoubleExtra("latitude", 0);
            Log.d("latitude", String.valueOf(latitude));
        }
        if (getPlaces.getDoubleExtra("longitude", 0) != 0) {
            longitude = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("minTimeStay", 0) != 0) {
            minStay = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("maxTimeStay", 0) != 0) {
            maxStay = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("priorityIndex", 0) != 0) {
            priorityIndex = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        if (getPlaces.getDoubleExtra("addPlaceCheckBackTime", 0) != 0) {
            placeCheckBackTime = getPlaces.getDoubleExtra("longitude", 0);
            Log.d("longitude", String.valueOf(longitude));
        }
        LatLng newLocationAdded = new LatLng(latitude, longitude);
        if (latitude != 0 && longitude != 0) {
            selectedLocationObj.addPlaceInformation(addressname, String.valueOf(longitude), String.valueOf(latitude),
                    String.valueOf(minStay), String.valueOf(maxStay), String.valueOf(priorityIndex), String.valueOf(placeCheckBackTime));
            mMap.addMarker(new MarkerOptions().position(newLocationAdded).title(addressname));
        }

    }

    public void centerMapLocation(Location location, String title) {
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
    }

    public ArrayList<SelectedLocation> getSelectedLocationList(){
        return selectedLocations;
    }
}



package com.example.thedude.shopandeateryactivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        Intent intent = getIntent();
        final String location = intent.getStringExtra("location");
        final String distance = intent.getStringExtra("distance");

        // Add a marker in Sydney and move the camera
        final List<Double> Coordinates = getLocationFromAddress(location);

        //EatShop eatShop = new EatShop();
        //List<EatShop> eaters = eatShop.shopList(Coordinates.get(0),Coordinates.get(1),Integer.parseInt(distance));

        new AsyncTask<Void, Void, List<EatShop>>() {
            @Override
            protected List<EatShop> doInBackground(Void... params) {
                return EatShop.shopList(Coordinates.get(0),Coordinates.get(1),Integer.parseInt(distance));
            }
            @Override
            protected void onPostExecute(List<EatShop> result) {

                float zoomLevel = 16; //This goes up to 21

                LatLng sydney = new LatLng(Coordinates.get(0),Coordinates.get(1));
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in "+ location));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                for(int i = 0 ; i < result.size() ; i++ ) {

                    Log.i (result.get(i).get("EntryAddress"),"Hello");
                    createMarker(Double.parseDouble(result.get(i).get("Latitude")), Double.parseDouble(result.get(i).get("Longitude")), result.get(i).get("EntryAddress"));
                }
            }
        }.execute();

    }

    public List<Double> getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        List<Double> latlong = new ArrayList<Double>();

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            latlong.add(latitude);
            latlong.add(longitude);


        }catch(Exception e){

        }


        return latlong;
    }

    protected Marker createMarker(double latitude, double longitude, String title) {

        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title));
    }
}

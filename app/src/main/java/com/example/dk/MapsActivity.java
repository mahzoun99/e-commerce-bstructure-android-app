package com.example.dk;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent goback=new Intent(this,Listekala);
        Button done = findViewById(R.id.goBack);

        if(checkPlayServices()) {
            setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        final Intent goBACK = new Intent(this,Listekala.class);
        final Button goBack =  findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(goBACK);
            }
        });
    }

    private boolean checkMapIsReady() {
        if(mMap==null)
        {
            Toast.makeText(getApplicationContext(),"Map Is Not Ready",Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }

    private Address gotoLocation(String searchString){
        Geocoder gc=new Geocoder(this);
        List<Address> list=null;
        try {
            list=gc.getFromLocationName(searchString,1);
            if(list.size()>0)
            {
                Address add=list.get(0);
                return add;
            }
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI=GoogleApiAvailability.getInstance();
        int result=googleAPI.isGooglePlayServicesAvailable(this);
        if(result== ConnectionResult.SUCCESS)
            return true;

        else{
            if(googleAPI.isUserResolvableError(result))
                googleAPI.getErrorDialog(this,result,9000).show();
            return false;

        }
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

        // Add a marker in Sydney  and move the camera
        LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //Address add=gotoLocation("tehran");
        LatLng destination=new LatLng(35.71,51.404343);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination,10));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
            //     mMap.addMarker(new MarkerOptions().position(latLng).title(""));
                Geocoder gc=new Geocoder(MapsActivity.this);
                List<Address> list=null;
                try {
                    list=gc.getFromLocation(latLng.latitude,latLng.longitude,1);
                    if(list.size()>0)
                    {
                        Address add=list.get(0);
                        addMarker(add);
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }


        });
    }
    private void addMarker(Address address){
        MarkerOptions markerOptions=new MarkerOptions()
                .position(new LatLng(address.getLatitude(),address.getLongitude()))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title(address.getLocality());
        mMap.addMarker(markerOptions);

    }


}

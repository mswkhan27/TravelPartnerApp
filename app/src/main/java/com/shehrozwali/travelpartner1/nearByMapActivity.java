package com.shehrozwali.travelpartner1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
public class nearByMapActivity extends AppCompatActivity {
    ImageButton routeButton;
    Spinner sptype;
    Button btnfind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    double cLong=0;
    double cLat=0;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_map);
        Intent intent = getIntent();
        final String placeName=intent.getStringExtra("name");
        final String placeSearch=intent.getStringExtra("search");

        sptype = findViewById(R.id.sp_type);
        btnfind = findViewById(R.id.findBtn);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag);

        final String[] placeList = {"atm", "restaurant", "hospital", "pharmacy", "diesel_station", "cng_station", "bank", "airport"};
        String[] placeNameList = {placeName};
        sptype.setAdapter(new ArrayAdapter<>(nearByMapActivity.this, android.R.layout.simple_spinner_dropdown_item, placeNameList));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            currentLocationGetter();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        int i=sptype.getSelectedItemPosition();
        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json"+
                "?location="+cLat+","+cLong+
                "&radius=5000"+
                "&types="+placeSearch+
                "&sensor=true"+
                "&key="+getResources().getString(R.string.google_maps_key);

        Log.d("SHEHROZ writes url", url);
        new PlaceTask().execute(url);
        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=sptype.getSelectedItemPosition();
                String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json"+
                        "?location="+cLat+","+cLong+
                        "&radius=5000"+
                        "&types="+placeSearch+
                        "&sensor=true"+
                        "&key="+getResources().getString(R.string.google_maps_key);

                Log.d("SHEHROZ writes url", url);
                new PlaceTask().execute(url);
            }
        });


    }

    private void currentLocationGetter() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> t = fusedLocationProviderClient.getLastLocation();
        t.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    cLat=location.getLatitude();
                    cLong=location.getLongitude();

                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map=googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(cLat,cLong),10
                            ));


                    }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==44){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                currentLocationGetter();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            String data=null;
            try {

                data=downloadUrl(strings[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {
        URL url=new URL(string);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.connect();
        InputStream stream=conn.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder=new StringBuilder();
        String line="";
        while((line=reader.readLine())!=null){
            builder.append(line);
        }
        String data=builder.toString();
        reader.close();
        return data;
    }

    private class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {

            JsonParser jsonParser=new JsonParser();
            List<HashMap<String,String>>mapList=null;
            JSONObject obj=null;
            try {
                obj=new JSONObject(strings[0]);
                mapList=jsonParser.parseResult(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            super.onPostExecute(hashMaps);
            map.clear();
            for (int i=0;i<hashMaps.size();i++){

                HashMap<String,String>hashMap=hashMaps.get(i);
                double lati=Double.parseDouble(hashMap.get("lat"));
                double longi=Double.parseDouble(hashMap.get("lng"));
                String name=hashMap.get("name");
                LatLng latLng=new LatLng(lati,longi);
                MarkerOptions opt=new MarkerOptions();
                opt.position(latLng);
                opt.title(name);

                map.addMarker(opt);
            }
        }
    }
}
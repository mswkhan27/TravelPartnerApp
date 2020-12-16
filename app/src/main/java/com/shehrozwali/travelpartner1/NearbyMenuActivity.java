package com.shehrozwali.travelpartner1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NearbyMenuActivity extends AppCompatActivity {
ImageButton placeMapButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_menu);
        Intent intent=getIntent();



    }


    public void restBtn(View view) {

        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","Restaurant");
        intent.putExtra("search","restaurant");
        startActivity(intent);

    }

    public void airBtn(View view) {
        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","Airport");
        intent.putExtra("search","airport");
        startActivity(intent);
    }

    public void cngBtn(View view) {
        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","CNG");
        intent.putExtra("search","gas_station");
        startActivity(intent);
    }

    public void pharBtn(View view) {
        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","Pharmacy");
        intent.putExtra("search","pharmacy");
        startActivity(intent);

    }

    public void atmBtn(View view) {
        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","ATM");
        intent.putExtra("search","atm");
        startActivity(intent);
    }

    public void dieselBtn(View view) {
        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","Diesel");
        intent.putExtra("search","gas_station");
        startActivity(intent);
    }

    public void hosBtn(View view) {
        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","Hospital");
        intent.putExtra("search","hospital");
        startActivity(intent);
    }

    public void bankBtn(View view) {
        Intent intent=new Intent(NearbyMenuActivity.this,nearByMapActivity.class);
        intent.putExtra("name","Bank");
        intent.putExtra("search","bank");
        startActivity(intent);
    }
}
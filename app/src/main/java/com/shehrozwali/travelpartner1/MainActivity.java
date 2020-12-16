package com.shehrozwali.travelpartner1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
    }

    public void exit(View view) {

        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(MainActivity.this,Login.class);
        startActivity(i);
    }

    public void myLocation(View view) {
        Intent intent=new Intent(MainActivity.this,LocationMap.class);
        startActivity(intent);


    }

    public void nearByButton(View view) {
        Intent intent=new Intent(MainActivity.this,NearbyMenuActivity.class);
        startActivity(intent);

    }
    public void wonderWorlButton(View view) {
        Intent intent=new Intent(MainActivity.this,WonderWorldActivity.class);
        startActivity(intent);

    }



}
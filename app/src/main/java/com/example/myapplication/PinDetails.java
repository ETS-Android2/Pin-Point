package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.ArrayList;

public class PinDetails extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;

    private double latitude;
    private double longitude;
    Toolbar toolbar;


    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_details);
        runToolBar();


        Intent intent = getIntent();
        String pinBody = intent.getExtras().getString("pinBody");
        TextView body= findViewById(R.id.pinBody);
        body.setText(pinBody);

        ArrayList<String> pinImg = intent.getStringArrayListExtra("pinImg");
        Log.i("pinImg", String.valueOf(pinImg.size()));

        String pinUser = intent.getExtras().getString("user");
        TextView userName= findViewById(R.id.pin_user2);
        userName.setText(pinUser);

        String date = intent.getExtras().getString("date");
        TextView dateText= findViewById(R.id.pinTime);
        dateText.setText(date);

//        String body = intent.getExtras().getString("body");


        RecyclerView recyclerView = findViewById(R.id.pinImgRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                recyclerView.setAdapter(new PinImagesAdapter(pinImg, PinDetails.this));
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        String userImg = intent.getExtras().getString("userImg");
        ImageView imageView = findViewById(R.id.post_UserName2);
        Amplify.Storage.downloadFile(
                userImg,
                new File(getApplicationContext().getFilesDir() + userImg),
                result -> {
                    Log.i("MyAmplifyApp", "Pin Image Successfully downloaded: " + result.getFile().getName());
                    imageView.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                    handler.sendEmptyMessage(1);

                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );



        latitude = intent.getExtras().getDouble("lat");
        longitude = intent.getExtras().getDouble("lon");

        if (latitude != 0 && longitude != 0) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            assert mapFragment != null;
            mapFragment.getMapAsync(this);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng location = new LatLng(latitude, longitude);
        Log.i("LatLng", latitude+" , " +longitude);

        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title("Location"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        // ---------------
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12));
//        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    void runToolBar() {
        toolbar = (Toolbar) findViewById(R.id.followersBar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
    }
}
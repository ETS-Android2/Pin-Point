package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Pin;
import com.amplifyframework.datastore.generated.model.Place;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewPin extends AppCompatActivity {
    Toolbar toolbar;
    TextView pinBody;
    Button imagesBtn;
    Button pinItBtn;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch privacySwitch;
    String countryNameStorage;
    String cityNameStorage;
    List<String> imageUris =new ArrayList<>();
    private FusedLocationProviderClient fusedLocationClient;
    private double latitude;
    private double longitude;


    Uri dataUri;
    File file;
    String fileName;
    String userId;
    User meUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pin);
        runToolBar();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(NewPin.this);
        SharedPreferences.Editor sharedEditor = sharedPreferences.edit();
        userId = sharedPreferences.getString("Id","  ");

        pinBody = findViewById(R.id.postInput);
        imagesBtn = findViewById(R.id.post_Image);
        pinItBtn = findViewById(R.id.submit_post);
        privacySwitch = findViewById(R.id.simpleSwitch);
//        Log.i("GO", isGooglePlayServicesAvailable());
        imagesBtn.setOnClickListener(view -> pickFile());
        pinItBtn.setOnClickListener(view -> createNewPin());
    }

    void runToolBar() {
        toolbar = (Toolbar) findViewById(R.id.posttoolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_reply_24); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {

        // check if permissions are given
        if (checkPermissions()) {
            // check if location is enabled
            if (isLocationEnabled()) {
                fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            Geocoder geocoder = new Geocoder(NewPin.this, Locale.getDefault());
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            Log.i("getLastLocation", "onCreate: latitude => " + latitude);
                            Log.i("getLastLocation", "onCreate: longitude => " + longitude);
                            try {
                                List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                                Address obj = address.get(0);
                                countryNameStorage = obj.getCountryName();
                                cityNameStorage = obj.getLocality();
                                Log.i("Location", "countryNameStorage--->> " + countryNameStorage);
                                Log.i("Location", "cityNameStorage--->> " + cityNameStorage);
//                                Log.i("Location", "cityNameStorage--->> " + );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED;
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5);
        locationRequest.setFastestInterval(0);
        locationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
    }

    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 0);
    }

    private void createNewPin() {
//        Latitude: 1 deg = 110.574 km
//        Longitude: 1 deg = 111.320*cos(latitude) km
        double approxLat = latitude - (5 / 110.574);
        double approxLon = longitude - (5 / 111.320 * Math.cos(latitude));
        Amplify.API.query(
                ModelQuery.get(User.class,userId),
                response -> {
                    meUser= response.getData();
                    Place newPlace = Place.builder().address(cityNameStorage + ", " + countryNameStorage).city(cityNameStorage).country(countryNameStorage).approxlat(approxLat).approxlon(approxLon).build();
                    Pin newPin = Pin.builder().place(newPlace).body(pinBody.getText().toString()).lat(latitude).lon(longitude).locatName(cityNameStorage + ", " + countryNameStorage).user(meUser).pinImg(imageUris).isPrivate(privacySwitch.isChecked()).build();
                    Amplify.API.mutate(
                            ModelMutation.create(newPlace),
                            response1 -> Log.i("CreatePin", "Added Todo with id: " + response1.getData().getAddress()),
                            error -> Log.e("CreatePin", "Create failed", error)
                    );
                    Amplify.API.mutate(
                            ModelMutation.create(newPin),
                            response1 -> Log.i("CreatePin", "Added Todo with id: " + response1.getData().getLocatName()),
                            error -> Log.e("CreatePin", "Create failed", error)
                    );

                },
                error -> Log.e("MyAmplifyApp", "Team Query failure", error)
        );
        Intent goToProfile = new Intent(NewPin.this, User_Page.class);
        startActivity(goToProfile);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        if (data.getClipData() != null) {

            int pickedImagesNumber = data.getClipData().getItemCount();
            for (int i = 0; i < pickedImagesNumber; i++) {

//                dataUri = data.getData();

                dataUri = data.getClipData().getItemAt(i).getUri();
                file = new File(dataUri.getPath());
                fileName = file.getName();
                Log.i("0000000000",fileName);
//                String imageString = dataUri.toString();
                imageUris.add(fileName);
                Log.i("IMAGE", "Successfully uploaded: " + fileName);
                try {
                    InputStream exampleInputStream = getContentResolver().openInputStream(dataUri);
                    Amplify.Storage.uploadInputStream(
                            fileName,
                            exampleInputStream,
                            result -> {
                                Log.i("0000000000", "Successfully uploaded: " + result.getKey());
                            },
                            storageFailure -> Log.e("0000000000", "Upload failed", storageFailure)
                            //circleImageView
                    );
                } catch (FileNotFoundException error) {
                    Log.e("0000000000", "Could not find file to open for input stream.", error);
                }
                // System.out.println("------------------IF FOR----------------"+image);
            }

            System.out.println("------------------IF----------------" + imageUris);
        } else {
            // for single image
            dataUri = data.getData();
            file = new File(dataUri.getPath());
            fileName = file.getName();
            imageUris.add(fileName);
            Log.i("0000000000", "single image "+ imageUris);
        }

//        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("IntentReset")
    private void pickFile() {
//        @SuppressLint("IntentReset") Intent selectedFile = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        selectedFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        selectedFile.setType(("*/*"));
//        selectedFile = Intent.createChooser(selectedFile, "Select File");
//        startActivityForResult(selectedFile, 1234);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1234);

    }

}
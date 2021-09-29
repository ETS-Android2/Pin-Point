package com.example.myapplication;

import android.app.ActionBar;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Pin;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    List<Pin> taskList = new ArrayList();
    User me;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        allNavationBarFunctions();
        buttonNavigationfun();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
        SharedPreferences.Editor sharedEditor = sharedPreferences.edit();
//        String userName = com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
        String userName = sharedPreferences.getString("userName", "  ");
        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.eq(userName)),
                response -> {
                    Log.i("MyAmplifyApp", response.getData().toString());
                    for (User user : response.getData()) {
                        me = user;
                        System.out.println("mmmmmmmm" + me.getFirstName());
                        Log.i("MyAmplifyApp", "hh");

                    }

                    sharedEditor.putString("Id", me.getId());
                    sharedEditor.putString("firstName", me.getFirstName());
                    sharedEditor.putString("bio", me.getBio());
                    sharedEditor.putString("Img", me.getImg());
                    sharedEditor.putString("LastName", me.getLastName());
                    sharedEditor.putString("followers", String.valueOf(me.getFollowers().size()));
                    sharedEditor.putString("following", String.valueOf(me.getFollowings().size()));

                    sharedEditor.apply();

                    System.out.println("zzzzzzzzzz" + me.getImg());
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_logout:
                logout();
                break;
            case R.id.nav_profile:
                goToProfile();
                break;
            case R.id.profile_butt:
                goToProfile();
                break;
            case R.id.home_butt:
                break;
            case R.id.discove_butt:
                goToDicover();
                break;
            case R.id.pin_butt:
                goToPin();
                break;
        }

        return true;
    }


    public void logout() {
        Amplify.Auth.signOut(
                () -> {
                    Log.i("logout", "successfully logout");
                    Intent goToLogin = new Intent(getBaseContext(), Login.class);
                    startActivity(goToLogin);
                    finish();
                },
                error -> Log.e("logout", error.toString())
        );
    }

    public String getCurrentValue() {
        AuthUser authUser = Amplify.Auth.getCurrentUser();
//        Log.e("getCurrentUser", authUser.toString());
//        Log.e("getCurrentUser", authUser.getUserId());
//        Log.e("getCurrentUser", authUser.getUsername());
        return authUser.getUsername();
    }

    public void goToProfile() {
        Intent gotoProfile = new Intent(Dashboard.this, User_Page.class);
        startActivity(gotoProfile);
    }

    public void goToDicover() {
        Intent gotoDiscoverPage = new Intent(Dashboard.this, Discover.class);
        startActivity(gotoDiscoverPage);
    }

    public void goToPin() {
        Intent gotoDiscoverPage = new Intent(Dashboard.this, NewPin.class);
        startActivity(gotoDiscoverPage);
    }


    public void allNavationBarFunctions() {
        /*----------Hooks---------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_veiw);
        toolbar = findViewById(R.id.toolbar3);



        /*----------toolBar---------*/
        setSupportActionBar(toolbar);

        /*----------NavationDrawerMenu---------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        TextView UserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.use_name_name);
        ImageView userImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.use_image);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
        //FullName
        String firstName = sharedPreferences.getString("firstName", "user");
        String LastName = sharedPreferences.getString("LastName", "Name");
        UserName.setText(firstName + " " + LastName);
        String img = sharedPreferences.getString("Img", "10");
        Log.i("Why",img);
        Amplify.Storage.downloadFile(
                img,
                new File(getApplicationContext().getFilesDir() + img),
                result -> {
                    System.out.println("jjj" + sharedPreferences.getString("Img", "10"));
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    userImage.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                    System.out.println("jjj" + sharedPreferences.getString("Img", "10"));
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );

        RecyclerView recyclerView = findViewById(R.id.dashboardRecycleVeiw);
        recyclerView.setAdapter(new PinAdapter(taskList, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void buttonNavigationfun() {
        bottomNavigationView = findViewById(R.id.butt_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.home_butt);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

//    public void imageBar(){
//        androidx.appcompat.app.ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
//        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Veiw v=inflater.inflate(R.layout.cutom_image,null);
//        actionBar.setCustomView(v);
//
//    }
}
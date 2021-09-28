package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Pin;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class User_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    List<Pin> taskList=new ArrayList();
    User me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        TextView followers=findViewById(R.id.followers);
        TextView following=findViewById(R.id.following);
        TextView fullName=findViewById(R.id.full_name);
        TextView bio=findViewById(R.id.bio_user_page);
        ImageView userImage=findViewById(R.id.usepage_image);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(User_Page.this);
        followers.setText(sharedPreferences.getString("followers", "0"));
        following.setText(sharedPreferences.getString("following","10"));
        //FullName
        String firstName=sharedPreferences.getString("firstName", "user");
        String LastName=sharedPreferences.getString("LastName", "Name");
        fullName.setText(firstName+" "+LastName);
        //Bio
        bio.setText(sharedPreferences.getString("bio","10"));
        //img
        Amplify.Storage.downloadFile(
                sharedPreferences.getString("Img","10"),
                new File(getApplicationContext().getFilesDir() + sharedPreferences.getString("Img","10")),
                result -> {
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    userImage.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                    System.out.println(sharedPreferences.getString("Img","10"));
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );

        allNavationBarFunctions();
        buttonNavigationfun();
        RelativeLayout followingFromProfile = findViewById(R.id.followingFromProfile);
        followingFromProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page.this,Following.class);
                startActivity(intent);
            }
        });

        RelativeLayout followersFromProfile = findViewById(R.id.followersFromProfile);
        followersFromProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page.this,Followers.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else {
//            Intent a = new Intent(Intent.ACTION_MAIN);
//            a.addCategory(Intent.CATEGORY_HOME);
//            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(a);
//        }
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                logout();
                break;
            case R.id.nav_profile:
                goToProfile();
                break;
            case R.id.nav_home:
                goToHome();
                break;
            case R.id.home_butt:
                goToHome();
                break;
            case R.id.discove_butt:
                goToDicover();
                break;
            case R.id.profile_butt:
                goToProfile();
                break;
            case R.id.pin_butt:
                goToPin();
                break;
        }

        return true;
    }


    public void logout(){
        Amplify.Auth.signOut(
                () ->{
                    Log.i("logout", "successfully logout");
                    Intent goToLogin = new Intent(getBaseContext(), Login.class);
                    startActivity(goToLogin);
                    finish();
                } ,
                error -> Log.e("logout", error.toString())
        );
    }

//    public String getCurrentValue(){
//        AuthUser authUser=Amplify.Auth.getCurrentUser();
//        Log.e("getCurrentUser", authUser.toString());
//        Log.e("getCurrentUser", authUser.getUserId());
//        Log.e("getCurrentUser", authUser.getUsername());
//        return authUser.getUsername();
//    }
    public void goToProfile(){
        Intent gotoProfile=new Intent(User_Page.this,User_Page.class);
        startActivity(gotoProfile);
    }
    public void goToDicover(){
        Intent gotoDiscoverPage=new Intent(User_Page.this,Discover.class);
        startActivity(gotoDiscoverPage);
    }

    public void goToHome(){
        Intent goToHome=new Intent(User_Page.this,Dashboard.class);
        startActivity(goToHome);
    }
    public void goToPin(){
        Intent gotoDiscoverPage=new Intent(User_Page.this,NewPin.class);
        startActivity(gotoDiscoverPage);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            Intent a = new Intent(User_Page.this,Dashboard.class);
            startActivity(a);

        }
    }


    public void allNavationBarFunctions(){
        /*----------Hooks---------*/
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_veiw);
        toolbar=findViewById(R.id.toolbar3);


        /*----------toolBar---------*/
        setSupportActionBar(toolbar);

        /*----------NavationDrawerMenu---------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        TextView UserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.use_name_name);
//        UserName.setText(getCurrentValue());

        RecyclerView recyclerView=findViewById(R.id.dashboardRecycleVeiw);
        recyclerView.setAdapter(new PinAdapter(taskList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void buttonNavigationfun(){
        bottomNavigationView=findViewById(R.id.butt_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.profile_butt);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

}
package com.example.myapplication;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Pin;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Variables
     DrawerLayout drawerLayout;
     NavigationView navigationView;
     Toolbar toolbar;
     List<Pin> taskList=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        allNavationBarFunctions();
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_logout:
                logout();
                break;
            case R.id.nav_discover:
                goToDicover();
                break;
            case R.id.nav_profile:
                goToProfile();
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

    public String getCurrentValue(){
        AuthUser authUser=Amplify.Auth.getCurrentUser();
        Log.e("getCurrentUser", authUser.toString());
        Log.e("getCurrentUser", authUser.getUserId());
        Log.e("getCurrentUser", authUser.getUsername());
        return authUser.getUsername();
    }
    public void goToProfile(){
        Intent gotoProfile=new Intent(Dashboard.this,User_Page.class);
        startActivity(gotoProfile);
    }
    public void goToDicover(){
        Intent gotoDiscoverPage=new Intent(Dashboard.this,Discover.class);
        startActivity(gotoDiscoverPage);
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
        UserName.setText(getCurrentValue());

        RecyclerView recyclerView=findViewById(R.id.dashboardRecycleVeiw);
        recyclerView.setAdapter(new PinAdapter(taskList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
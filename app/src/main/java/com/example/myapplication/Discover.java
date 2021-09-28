package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.auth.AuthUser;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Discover extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    EditText searchVew;
    CharSequence search = "";
    DiscoverAdapter discoverAdapter;
    RecyclerView recyclerView;
    Dashboard dashboard = new Dashboard();
    List<User> usersList = new ArrayList<>();
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
//        RecyleView();
        allNavationBarFunctions();
        buttonNavigationfun();
    }

    @Override
    protected void onStart() {
        super.onStart();
        gettingUsers();
        recyclerView = findViewById(R.id.discoverRecycleVeiw);
        recyclerView.setAdapter(new DiscoverAdapter(usersList, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


//    public void RecyleView() {
//        recyclerView = findViewById(R.id.discoverRecycleVeiw);
//        recyclerView.setAdapter(new DiscoverAdapter(usersList, this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        gettingUsers();
//
//
//    }

    Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            recyclerView.getAdapter().notifyDataSetChanged();
            return false;
        }
    });

    public void gettingUsers() {
        Amplify.API.query(
                ModelQuery.list(User.class),
                response -> {
                    for (User user : response.getData()) {
                        if (!getLoggedInUSer().equals(user.getUserName())){
                            usersList.add(user);
                            handler.sendEmptyMessage(1);
                            Log.i("MyAmplifyApp", user.getFirstName());
                        }
                        
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }

//    public void searchBarFunc(){
//        searchVew=findViewById(R.id.search_bar);
//        searchVew.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                discoverAdapter.getFliter().filter(charSequence);
//                search=charSequence;
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.nav_home:
//                break;
            case R.id.nav_logout:
                logout();
                break;
            case R.id.nav_discover:
                goToDicover();
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
                break;
            case R.id.profile_butt:
                goToProfile();
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
        Log.e("getCurrentUser", authUser.toString());
        Log.e("getCurrentUser", authUser.getUserId());
        Log.e("getCurrentUser", authUser.getUsername());
        return authUser.getUsername();
    }

    public void goToProfile() {
        Intent gotoProfile = new Intent(Discover.this, User_Page.class);
        startActivity(gotoProfile);
    }

    public void goToDicover() {
        Intent gotoDiscoverPage = new Intent(Discover.this, Discover.class);
        startActivity(gotoDiscoverPage);
    }

    public void goToHome() {
        Intent goToHome = new Intent(Discover.this, Dashboard.class);
        startActivity(goToHome);
    }

    public void buttonNavigationfun() {
        bottomNavigationView = findViewById(R.id.butt_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.discove_butt);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
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
        UserName.setText(getLoggedInUSer());


    }

    String getLoggedInUSer() {
        return com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
    }
}


/*

public class Discover extends AppCompatActivity {
    RecyclerView recyclerView;
    List<User> usersList = new ArrayList<>();
    User meUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

//        String userName=com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
//        Amplify.API.query(
//                ModelQuery.list(User.class, User.USER_NAME.eq(userName)),
//                response -> {
//                    for (User user : response.getData()) {
//                        meUser=user;
//                    }
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );



    }

    @Override
    protected void onStart() {
        super.onStart();
        gettingUsers();
        recyclerView = findViewById(R.id.DicoverRecycleVeiw);
        recyclerView.setAdapter(new DiscoverAdapter(usersList, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            recyclerView.getAdapter().notifyDataSetChanged();
            return false;
        }
    });

    public void gettingUsers() {
        Amplify.API.query(
                ModelQuery.list(User.class),
                response2 -> {
                    for (User user : response2.getData()) {
                        usersList.add(user);
                        Log.i("MyAmplifyApp", user.getFirstName());
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }
}
 */
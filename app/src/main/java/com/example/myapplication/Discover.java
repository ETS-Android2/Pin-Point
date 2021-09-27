package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

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
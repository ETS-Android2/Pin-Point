package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
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

public class Following extends AppCompatActivity {
    List<User> users=new ArrayList<>();
    List<String> followingsIds = new ArrayList<>();
    User me;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);


        RecyclerView allUsersRecyclerView = findViewById(R.id.followingRecycle);

        Handler handler =new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allUsersRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        allUsersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allUsersRecyclerView.setAdapter(new DiscoverAdapter(users,this));


        String userName = com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.eq(userName)),
                response -> {
                    for (User user : response.getData()) {
                        me =user;
                        Log.i("MyAmplifyApp", "hh");

                    }
                    System.out.println("hhhhhhhhhhhhhhhhhh"+ me.getFollowings().size());
                    for (int i = 0; i < me.getFollowings().size(); i++) {
                        followingsIds.add(me.getFollowings().get(i).getUserFollowing());
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


        Amplify.API.query(
                ModelQuery.list(User.class),
                response -> {
                    System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+ followingsIds.toString());
                    response.getData().forEach(value -> {
                        if (followingsIds.contains(value.getId())){
                            users.add(value);
                        }
                    });
                    System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+ users.size());
                    handler.sendEmptyMessage(1);

//                    for (User user : response.getData()) {
//                        for (int i = 0; i < me.getFollowers().size(); i++) {
//                            if (user.getId().equals(me.getFollowers().get(i).getUserFollower())){
//                                users.add(user);
//                            }
//                        }
//                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }



}
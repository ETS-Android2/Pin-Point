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

public class Followers extends AppCompatActivity {
    List<User> users=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);


        RecyclerView allUsersRecyclerView = findViewById(R.id.followersRecycle);

        Handler handler =new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allUsersRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        allUsersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allUsersRecyclerView.setAdapter(new FollowersAdapter(users));


        // modify the condition
        Amplify.API.query(
                ModelQuery.get(User.class,"hello"),
                response ->{
                    Log.i("MyAmplifyApp", "okay");
                    users=response.getData().getFollowers();
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );


    }
}
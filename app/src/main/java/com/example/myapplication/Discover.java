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
    List<User> usersList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        recyclerView=findViewById(R.id.DicoverRecycleVeiw);
        recyclerView.setAdapter(new DiscoverAdapter(usersList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gettingUsers();


    }

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

    public void gettingUsers(){
      Amplify.API.query(
              ModelQuery.list(User.class),
    response -> {
        for (User user : response.getData()) {
            usersList.add(user);
            handler.sendEmptyMessage(1);
            Log.i("MyAmplifyApp", user.getFirstName());
        }
    },
    error -> Log.e("MyAmplifyApp", "Query failure", error)
            );
    }
}
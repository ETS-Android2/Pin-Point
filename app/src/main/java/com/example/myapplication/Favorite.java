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

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Pin;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {
    List<Pin> listPinFav=new ArrayList<>();
   User me;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        RecyclerView allFavRecyclerView = findViewById(R.id.recyclerViewFavoriteId);
        allFavRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String authUser = com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allFavRecyclerView.setAdapter(new PinAdapter(listPinFav, Favorite.this));

                allFavRecyclerView.getAdapter().notifyDataSetChanged();

                return false;
            }
        });



        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.eq(authUser)),
                response -> {
                    for (User user : response.getData()) {
                       me=user;
                        System.out.println("jjjjjjjjjjjjjjj"+me.getFavorites().size());
                    }  me.getFavorites().forEach(item->{
                        System.out.println("hhhhhhhhhhhhhhhhhhhhhhh"+item.getPin().getBody());
                        listPinFav.add(item.getPin());
                    });
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }
}
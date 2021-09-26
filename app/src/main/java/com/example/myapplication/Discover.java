package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class Discover extends AppCompatActivity {

    List<User> usersList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        RecyclerView recyclerView=findViewById(R.id.DicoverRecycleVeiw);
        recyclerView.setAdapter(new DiscoverAdapter(usersList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
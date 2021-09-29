package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.UserViewHolder>{
    List<User> allUsers = new ArrayList<>();

    public FollowersAdapter(List<User> allUsers){
        this.allUsers = allUsers;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        public User user;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }


    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_followers,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.user = allUsers.get(position);

        ImageView taskButton = holder.itemView.findViewById(R.id.followerImg);
        TextView followerName= holder.itemView.findViewById(R.id.followerName);
        followerName.setText(holder.user.getFirstName()+" "+ holder.user.getLastName());

    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }
}

package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Follower;
import com.amplifyframework.datastore.generated.model.Following;
import com.amplifyframework.datastore.generated.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.UserVeiwHolder> {

    List<User> users = new ArrayList<User>();
    Context context;
    static User meUser;


    public DiscoverAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public static class UserVeiwHolder extends RecyclerView.ViewHolder {
        View itemView;
        ConstraintLayout mainLayout;
        public User user;

        public UserVeiwHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            Button follow = itemView.findViewById(R.id.followButtonFragmentId);
            Button unfollow = itemView.findViewById(R.id.unfollowButtonFragmentId);


            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                   User userCopy=meUser.copyOfBuilder().
                    Following followingUser = Following.builder().user(meUser).userFollowing(user.getId()).build();
                    Follower followerUser = Follower.builder().user(user).userFollower(meUser.getId()).build();
                    Amplify.API.mutate(ModelMutation.create(followingUser),
                            response1 -> Log.i("following", "following with id: " + response1.getData().getId()),
                            error -> Log.e("following", "Create failed", error)
                    );
                    Amplify.API.mutate(ModelMutation.create(followerUser),
                            response2 -> Log.i("follower", "follower with id: " + response2.getData().getId()),
                            error -> Log.e("follower", "Create failed", error)
                    );
                }


//                    toDetailsPage.putExtra("title",task.getTitle());
//                    toDetailsPage.putExtra("body",task.getBody());
//                    toDetailsPage.putExtra("state",task.getState());
//                    toDetailsPage.putExtra("imgName",task.getImgName());
//                    toDetailsPage.putExtra("lon",task.getLon());
//                    toDetailsPage.putExtra("lat",task.getLat());
//
//                    v.getContext().startActivity(toDetailsPage);
//                }
            });
//            mainLayout=itemView.findViewById(R.id.pin_original);
        }
    }

    @NonNull
    @Override
    public UserVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_discover, parent, false);
        UserVeiwHolder taskVeiwHolder = new UserVeiwHolder(view);
        return taskVeiwHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserVeiwHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.user = users.get(position);
        TextView taskTitle = holder.itemView.findViewById(R.id.nameOfUser);
        taskTitle.setText(holder.user.getFirstName() + " " + holder.user.getLastName());

        Button follow = holder.itemView.findViewById(R.id.followButtonFragmentId);
        Button unfollow = holder.itemView.findViewById(R.id.unfollowButtonFragmentId);

        Amplify.Storage.downloadFile(
                holder.user.getImg(),
                new File(context.getApplicationContext().getFilesDir() + holder.user.getImg()),
                result -> {
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    ImageView image = holder.itemView.findViewById(R.id.use_image);
                    // https://stackoverflow.com/questions/19172154/convert-a-file-object-to-bitmap
                    image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                    //https://github.com/bumptech/glide
//                    Glide.with(TaskDetailPage.this).load(result.getFile().getPath()).centerCrop().into(image);
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );

        String userName = com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.eq(userName)),
                response -> {
                    for (User x : response.getData()) {
                        meUser = x;
                        Log.i("TestUser", meUser.getFollowings().toString());
                    }
                    Log.i("TestUser", String.valueOf(!meUser.getFollowings().isEmpty()));

//                        follow.setVisibility(View.VISIBLE);
//                        unfollow.setVisibility(View.INVISIBLE);
                    if (!meUser.getFollowings().isEmpty()) {
                        for (Following following : meUser.getFollowings()) {
                            if (following.getUserFollowing().equals(holder.user.getId())) {
                                follow.setVisibility(View.INVISIBLE);
//                                unfollow.setVisibility(View.VISIBLE);
//                                unfollow.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
//                    else{
////                        unfollow.setVisibility(View.INVISIBLE);
//                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        //        holder.taskModel=pins.get(position);
//        ImageView map=holder.itemView.findViewById(R.id.map_id);
//        map.setImageBitmap(holder.taskModel.getTitle());
//        TextView taskbody=holder.itemView.findViewById(R.id.bodyView);
//        taskbody.setText(holder.taskModel.getBody());
//        TextView taskstate=holder.itemView.findViewById(R.id.stateView);
//        taskstate.setText(holder.taskModel.getState());


//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, TaskDetailPage.class);
//                if (allTask.get(position).getLocation().getName() != null) {
//                    intent.putExtra("taskLocation", allTask.get(position).getLocation().getName());
//                } else {
//                    intent.putExtra("taskLocation","");
//                }
////                intent.putExtra("taskLocation",allTask.get(position).getLocation().getName());
//                intent.putExtra("title",allTask.get(position).getTitle() );
//                intent.putExtra("body", allTask.get(position).getBody());
//                intent.putExtra("state", allTask.get(position).getState());
//                intent.putExtra("img", allTask.get(position).getImg());
//                context.startActivity(intent);
//            }
//        });
//
    }

    public static User getMyUser() {
        ArrayList<User> meUserList = new ArrayList<>();

        String userName = com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername();
        Amplify.API.query(
                ModelQuery.list(User.class, User.USER_NAME.eq(userName)),
                response -> {
                    for (User user : response.getData()) {
                        meUserList.add(user);
                        Log.i("MyAmplifyApp", "hh");
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        Log.i("GetUser", meUserList.get(0).getUserName());

        return meUserList.get(0);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}

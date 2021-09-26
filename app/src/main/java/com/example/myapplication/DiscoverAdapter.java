package com.example.myapplication;



import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.UserVeiwHolder> {

    List<User> users=new ArrayList<User>();
    Context context;

    public DiscoverAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public static class UserVeiwHolder extends RecyclerView.ViewHolder {
        View itemView;
        ConstraintLayout mainLayout;
        public User taskModel;

        public UserVeiwHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
//            mainLayout=itemView.findViewById(R.id.pin_original);
        }
    }

    @NonNull
    @Override
    public UserVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_discover,parent,false);
        UserVeiwHolder taskVeiwHolder=new UserVeiwHolder(view);
        return taskVeiwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserVeiwHolder holder, @SuppressLint("RecyclerView") int position) {
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

    @Override
    public int getItemCount() {
        return 10;
    }

}

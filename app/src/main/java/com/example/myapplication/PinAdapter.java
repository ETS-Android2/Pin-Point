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

import com.amplifyframework.datastore.generated.model.Pin;

import java.util.ArrayList;
import java.util.List;


public class PinAdapter extends RecyclerView.Adapter<PinAdapter.PinVeiwHolder> {

    List<Pin> pins=new ArrayList<Pin>();
    Context context;

    public PinAdapter(List<Pin> pins, Context context) {
        this.pins = pins;
        this.context = context;
    }

    public static class PinVeiwHolder extends RecyclerView.ViewHolder {
        View itemView;
        ConstraintLayout mainLayout;
        public Pin taskModel;

        public PinVeiwHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
//            mainLayout=itemView.findViewById(R.id.pin_original);
        }
    }

    @NonNull
    @Override
    public PinVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank,parent,false);
        PinVeiwHolder taskVeiwHolder=new PinVeiwHolder(view);
        return taskVeiwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PinVeiwHolder holder, @SuppressLint("RecyclerView") int position) {
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
        return 4;
    }

}


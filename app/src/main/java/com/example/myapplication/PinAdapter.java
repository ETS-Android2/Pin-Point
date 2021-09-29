package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Pin;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PinAdapter extends RecyclerView.Adapter<PinAdapter.PinVeiwHolder> {

    List<Pin> pins=new ArrayList<>();
    Context context;
    static User meUser;
//    GoogleMap googleMap;

    public PinAdapter(List<Pin> pins, Context context) {
        this.pins = pins;
        this.context = context;
//        Log.i("ListCheck", String.valueOf(pins.size()));

    }

    public static class PinVeiwHolder extends RecyclerView.ViewHolder {
        View itemView;
        ConstraintLayout mainLayout;
        public Pin pin;

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
        Log.i("ListCheck", String.valueOf(pins.size()));
        return taskVeiwHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PinVeiwHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.i("ListCheck", String.valueOf(pins.size()));
        holder.pin = pins.get(position);
        TextView name = holder.itemView.findViewById(R.id.pin_user2);
        TextView pinTime = holder.itemView.findViewById(R.id.pinTime);
        pinTime.setText(holder.pin.getUser().getCreatedAt().format());
        name.setText(holder.pin.getUser().getFirstName()+" "+holder.pin.getUser().getLastName());
        name.setText(holder.pin.getUser().getFirstName()+" "+holder.pin.getUser().getLastName());
        ImageView userPic = holder.itemView.findViewById(R.id.post_UserName2);
        ImageView map = holder.itemView.findViewById(R.id.map_id);
        TextView pinBody = holder.itemView.findViewById(R.id.pin_text);
        pinBody.setText(holder.pin.getBody());
        Picasso.get().load("https://www.mapquestapi.com/staticmap/v5/map?key=jFGVoWIBIDazzPv5IdBX4AIDYXDwpE97&locations="+holder.pin.getLat()+","+holder.pin.getLon()+"&defaultMarker=marker-red-lg&format=png&zoom=15&size=400,400@2x").into(map);

        Amplify.Storage.downloadFile(
                holder.pin.getUser().getImg(),
                new File(context.getApplicationContext().getFilesDir() + holder.pin.getUser().getImg()),
                result -> {
                    Log.i("MyAmplifyApp", "USer Image Successfully downloaded: " + result.getFile().getName());
                    userPic.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );

        ImageView pinImg =holder.itemView.findViewById(R.id.pin_img);

        Amplify.Storage.downloadFile(
                holder.pin.getPinImg().get(0),
                new File(context.getApplicationContext().getFilesDir() + holder.pin.getPinImg().get(0)),
                result -> {
                    Log.i("MyAmplifyApp", "Pin Image Successfully downloaded: " + result.getFile().getName());
                    pinImg.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );

    }

    @Override
    public int getItemCount() {
        return pins.size();
    }
}


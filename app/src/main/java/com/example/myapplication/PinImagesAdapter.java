package com.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.Amplify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PinImagesAdapter extends RecyclerView.Adapter<PinImagesAdapter.ImgViewHolder>{
    ArrayList<String> imgs = new ArrayList<>();
    Context context;

    public PinImagesAdapter(ArrayList<String> imgs, Context context) {
        this.context = context;
        this.imgs = imgs;
    }

    public static class ImgViewHolder extends RecyclerView.ViewHolder {

        public String img;
        View itemView;

        public ImgViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }


    @NonNull
    @Override
    public ImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_pin_img,parent,false);
        ImgViewHolder imgViewHolder = new ImgViewHolder(view);
        return imgViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImgViewHolder holder, int position) {
        holder.img = imgs.get(position);
        ImageView pinImg = holder.itemView.findViewById(R.id.singleImg);

        Amplify.Storage.downloadFile(
                holder.img,
                new File(context.getApplicationContext().getFilesDir() + holder.img),
                result -> {
                    Log.i("MyAmplifyApp", "Pin Image" + result.getFile().getName());
                    pinImg.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );
    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }



}

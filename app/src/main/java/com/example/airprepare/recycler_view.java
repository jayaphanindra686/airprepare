package com.example.airprepare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class recycler_view extends RecyclerView.Adapter<recycler_view.viewholder> {
    public Context mcontext;
    String[] imageUrls;
    ArrayList<String> imgUrls;

    public recycler_view(Context context, ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
        mcontext = context;
    }

    @NonNull
    @Override
    public recycler_view.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_view.viewholder holder, int position) {
        Glide.with(mcontext).load(imgUrls.get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getImage());

    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }

        public ImageView getImage() {
            return this.imageView;
        }
    }
}

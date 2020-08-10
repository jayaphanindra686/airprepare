package com.example.airprepare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

public class ImageListAdapter extends ArrayAdapter {
    public String[] imageUrls;
    private Context mcontext;
    private LayoutInflater layoutInflater;

    public ImageListAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.listview, imageUrls);
        mcontext = context;
        this.imageUrls = imageUrls;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.listview, parent, false);
        }
        Glide.with(mcontext).load(imageUrls[position]).into((ImageView) convertView);
        return convertView;
    }
}

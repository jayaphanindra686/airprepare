package com.example.airprepare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {

    String[] url = {
            "https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2F1580376870827.jpg?alt=media&token=6df7e9b2-0a09-4d23-ae07-055f0f490b3f",
            "https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2F1580376883984.jpg?alt=media&token=7142d928-a10b-4694-a2b7-10ee80adc244",
            "https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2F1580376890100.jpg?alt=media&token=3ce387e2-0c8c-4b31-a235-c384f0926caa"
    };
    ImageView imageView;
    private Context mContext;
    private int[] mImageIds = new int[]{R.drawable.bundh, R.drawable.floods, R.drawable.elections};

    ImageAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View page = inflater.inflate(R.layout.activity_homescreen, null);
        imageView = new ImageView(mContext);
        //Glide.with(this).load(url[1]).into(imageView);
        imageView.setImageResource(mImageIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    Toast.makeText(mContext, "pressed 1", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    Toast.makeText(mContext, "pressed 2", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(mContext, "pressed 3", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}


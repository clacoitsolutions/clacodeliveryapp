package com.example.clacodelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.clacodelivery.R;

public class SplashViewPage extends PagerAdapter {

    Context context;

    int image[] = {
            R.drawable.image1,
            R.drawable.image4,
            R.drawable.image3
    };

    int heading[] = {
            R.string.heading_1,
            R.string.heading_2,
            R.string.heading_3
    };

    int description[] = {
            R.string.description_1,
            R.string.description_2,
            R.string.description_3
    };

    public SplashViewPage(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView imageSlide = view.findViewById(R.id.titleImage);
        TextView titleTextView  = view.findViewById(R.id.texttitile);
        TextView descriptionTextView   = view.findViewById(R.id.titiledesc);

        imageSlide.setImageResource(image[position]);
        titleTextView.setText(heading[position]);
        descriptionTextView .setText(description[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}

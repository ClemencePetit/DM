package com.example.testgallery.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.testgallery.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;


public class pictureBrowserFragment extends Fragment {

    private  ArrayList<String> allImages = new ArrayList<>();
    private int position;
    private Context animeContx;
    private ImageView image;

    public pictureBrowserFragment(){

    }

    public pictureBrowserFragment(ArrayList<String> allImages, int imagePosition, Context anim) {
        this.allImages = allImages;
        this.position = imagePosition;
        this.animeContx = anim;
    }

    public static pictureBrowserFragment newInstance(ArrayList<String> allImages, int imagePosition, Context anim) {
        pictureBrowserFragment fragment = new pictureBrowserFragment(allImages,imagePosition,anim);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.picture_browser, container, false);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LayoutInflater layoutinflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        image = view.findViewById(R.id.image);
        String pic = allImages.get(position);
        Glide.with(animeContx)
                .load(pic)
                .apply(new RequestOptions().fitCenter())
                .into(image);



    }








}

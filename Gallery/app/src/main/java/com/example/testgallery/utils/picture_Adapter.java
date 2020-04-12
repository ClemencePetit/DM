package com.example.testgallery.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.testgallery.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.testgallery.fragments.pictureBrowserFragment;

import java.util.ArrayList;
import static androidx.core.view.ViewCompat.setTransitionName;

/**
 * Author CodeBoy722
 *
 * A RecyclerView Adapter class that's populates a RecyclerView with images from
 * a folder on the device external storage
 */
public class picture_Adapter extends RecyclerView.Adapter<PicHolder> {

    private ArrayList<String> pictureList;
    private Context pictureContx;
    private final itemClickListener picListerner;


    public picture_Adapter(ArrayList<String> pictureList, Context pictureContx,itemClickListener picListerner) {
        this.pictureList = pictureList;
        this.pictureContx = pictureContx;
        this.picListerner = picListerner;
    }

    @NonNull
    @Override
    public PicHolder onCreateViewHolder(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View cell = inflater.inflate(R.layout.pic_holder_item, container, false);
        return new PicHolder(cell);
    }

    @Override
    public void onBindViewHolder(@NonNull final PicHolder holder, final int position) {

        final String image = pictureList.get(position);

        Glide.with(pictureContx)
                .load(image)
                .apply(new RequestOptions().centerCrop())
                .into(holder.picture);

        setTransitionName(holder.picture, String.valueOf(position) + "_image");


        holder.picture.setOnClickListener(new DoubleClickListener() {

         	@Override
            public void onDoubleClick() {
                picListerner.onPicClicked(holder,position, pictureList);


         	    }
 			});

    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

}
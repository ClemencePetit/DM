package com.example.testgallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Fade;
import android.view.View;
import android.widget.TextView;

import android.widget.Toast;

import com.example.testgallery.fragments.pictureBrowserFragment;
import com.example.testgallery.utils.MarginDecoration;
import com.example.testgallery.utils.PicHolder;
import com.example.testgallery.utils.itemClickListener;
import com.example.testgallery.utils.picture_Adapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements itemClickListener {

    RecyclerView imageRecycler;
    ArrayList<String> allpictures;

    TextView empty;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }


        empty =findViewById(R.id.empty);


        allpictures = new ArrayList<>();
        imageRecycler = findViewById(R.id.recycler);
        imageRecycler.addItemDecoration(new MarginDecoration(this));
        imageRecycler.hasFixedSize();

        if(allpictures.isEmpty()){
            allpictures = getPicturePaths();
            imageRecycler.setAdapter(new picture_Adapter(allpictures,MainActivity.this,this));
            if(allpictures.isEmpty())
            {
                empty.setVisibility(View.VISIBLE);
            }
        }
    }

    private ArrayList<String> getPicturePaths(){
        ArrayList<String> images = new ArrayList<>();
        Uri allVideosuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE};
        Cursor cursor = this.getContentResolver().query( allVideosuri, projection, null, null, null);
        try {
            cursor.moveToFirst();
            do{

                images.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
            }while(cursor.moveToNext());
            cursor.close();
            ArrayList<String> reSelection = new ArrayList<>();
            for(int i = images.size()-1;i > -1;i--){
                reSelection.add(images.get(i));
            }
            images = reSelection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }


    @Override
    public void onPicClicked(PicHolder holder, int position, ArrayList<String> pics) {
        Toast.makeText(this, "Pif", Toast.LENGTH_SHORT).show();
        pictureBrowserFragment browser = pictureBrowserFragment.newInstance(pics,position,this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            browser.setEnterTransition(new Fade());
            browser.setExitTransition(new Fade());
        }

        getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.picture, position+"picture")
                .add(R.id.displayContainer, browser)
                .addToBackStack(null)
                .commit();
    }


}

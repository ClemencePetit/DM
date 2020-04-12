package com.example.testgallery.utils;

import java.util.ArrayList;

/**
 * Author CodeBoy722
 */
public interface itemClickListener {


   void onPicClicked(PicHolder holder, int position, ArrayList<String> pics);
    //void onPicClicked(String pictureFolderPath,String folderName);
}

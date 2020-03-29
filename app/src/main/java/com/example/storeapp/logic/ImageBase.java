package com.example.storeapp.logic;

import android.widget.ImageView;

public class ImageBase {
    public static void loadImage(String imageName, ImageView imageView) {
        // search on Cache
        // search on Local Storage (update image last usage time, add to cache)
        // search on Web (add to cache)
    }

    public static void clearCache() {

    }

    private static void unloadImage() {
        // if not on local storage
        //  save image on Local Storage (Delete the older image, if overflow the limit)
        // delete image from cache
    }
}

package com.razor.black;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.IOException;

/**
 * Created by razor on 25/12/15.
 */
public class ImageLoader implements Runnable {

    public static final String TAG = ImageLoader.class.getSimpleName();

    private ImageToLoad imageToLoad;

    public ImageLoader(ImageToLoad imageToLoad) {
        this.imageToLoad = imageToLoad;
    }

    @Override
    public void run() {
        Bitmap bitmap = Utilities.getBitmapFromURL(imageToLoad.getImageUrl());
        Black.getInstance().getLruCache().addBitmapToMemoryCache(imageToLoad.getImageUrl(), bitmap);
        try {
            Black.getInstance().getDiskCache().putBitmap(imageToLoad.getImageUrl(), bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            Log.v(TAG, e.getMessage());
        }
        Black.getHANDLER().post(new BitmapDisplayer(imageToLoad,bitmap));
    }
}

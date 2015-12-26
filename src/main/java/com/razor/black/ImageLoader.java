package com.razor.black;

import android.graphics.Bitmap;
import android.support.annotation.WorkerThread;

/**
 * Created by razor on 25/12/15.
 */
public class ImageLoader implements Runnable {

    public static final String TAG = ImageLoader.class.getSimpleName();

    private ImageToLoad imageToLoad;

    public ImageLoader(ImageToLoad imageToLoad) {
        this.imageToLoad = imageToLoad;
    }

    @WorkerThread
    @Override
    public void run() {
        Logger.i(Black.TAG+" "+TAG,"Image Fetching started from Network.");
        Logger.i(Black.TAG, "Fetching Image: " + imageToLoad.getImageUrl());
        Bitmap bitmap = Utilities.getBitmapFromURL(imageToLoad.getImageUrl());
        if (bitmap != null){
            Black.getInstance().getLruCache().addBitmapToMemoryCache(imageToLoad.getImageUrl(), bitmap);
        }
        Black.getHANDLER().post(new BitmapDisplayer(imageToLoad, bitmap));
    }
}

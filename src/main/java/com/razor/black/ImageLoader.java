package com.razor.black;

import android.graphics.Bitmap;

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
        Black.getHANDLER().post(new BitmapDisplayer(imageToLoad,bitmap));
    }
}

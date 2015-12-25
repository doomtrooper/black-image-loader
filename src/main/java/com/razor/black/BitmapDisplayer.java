package com.razor.black;

import android.graphics.Bitmap;

/**
 * Created by razor on 25/12/15.
 */
public class BitmapDisplayer implements Runnable {

    private ImageToLoad imageToLoad;
    private Bitmap bitmap;

    public BitmapDisplayer(ImageToLoad imageToLoad, Bitmap bitmap) {
        this.imageToLoad = imageToLoad;
        this.bitmap = bitmap;
    }

    @Override
    public void run() {
        if (bitmap != null){
            imageToLoad.getTargetImageview().setImageBitmap(bitmap);
        }
    }
}

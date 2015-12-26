package com.razor.black;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

/**
 * Created by razor on 25/12/15.
 */
public class BitmapDisplayer implements Runnable {

    private ImageToLoad imageToLoad;
    private Bitmap bitmap;

    public BitmapDisplayer(ImageToLoad imageToLoad, @Nullable Bitmap bitmap) {
        this.imageToLoad = imageToLoad;
        this.bitmap = bitmap;
    }

    @UiThread
    @Override
    public void run() {
        if (bitmap != null){
            imageToLoad.getTargetImageview().setImageBitmap(bitmap);
        }else {
            if (Black.getErrorResourceDrawableId() != null){
                imageToLoad.getTargetImageview().setImageResource(Black.getErrorResourceDrawableId());
            }
        }
    }
}

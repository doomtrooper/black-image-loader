package com.razor.black;

import android.widget.ImageView;

/**
 * Created by razor on 25/12/15.
 */
public class ImageToLoad {
    private String imageUrl;
    private ImageView targetImageview;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageView getTargetImageview() {
        return targetImageview;
    }

    public void setTargetImageview(ImageView targetImageview) {
        this.targetImageview = targetImageview;
    }

    public ImageToLoad(String imageUrl, ImageView targetImageview) {

        this.imageUrl = imageUrl;
        this.targetImageview = targetImageview;
    }
}

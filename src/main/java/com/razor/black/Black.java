package com.razor.black;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by razor on 15/12/15.
 */
public class Black {

    /** Callbacks for Black events. */
    public interface Listener {
        /**
         * Invoked when an image has failed to load. This is useful for reporting image failures to a
         * remote analytics service, for example.
         */
        void onImageLoadFailed(Black black, Uri uri, Exception exception);
    }

    public static final String TAG = Black.class.getSimpleName();

    static volatile Black singleton = null;
    private final Listener mListener;
    private final Context mContext;
    private Map<ImageView,String> imageviewsTargetMap;
    static ExecutorService executorService;
    static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private final Object lock = new Object();

    private FileCache mLruCache;
    private boolean isLogEnabled = true;



    private Black(Context context) {
        this(context,null);
    }

    private Black(Context context, Listener listener) {
        this.mContext = context;
        this.mListener = listener;
        imageviewsTargetMap = new HashMap<>();
        executorService=Executors.newFixedThreadPool(5);
        mLruCache = FileCache.init();
    }

    public static Black init(Context context) {
        if (singleton==null){
            synchronized (Black.class){
                if (singleton==null){
                    singleton = new Black(context);
                }
            }
        }
        return singleton;
    }

    public boolean isLogEnabled() {
        return isLogEnabled;
    }

    public Black log(boolean isLog) {
        synchronized (lock){
            this.isLogEnabled = isLog;
        }
        return singleton;
    }

    public static Black getInstance(){
        return singleton;
    }

    public static Handler getHANDLER() {
        return HANDLER;
    }

    public FileCache getLruCache() {
        return mLruCache;
    }

    public void loadImage(ImageView imageView, String url) throws IOException {
        imageviewsTargetMap.put(imageView,url);
        Bitmap bmp = getBitmapFromCache(url);
        if (bmp != null){
            imageView.setImageBitmap(bmp);
        }else {
            executorService.submit(new ImageLoader(new ImageToLoad(url, imageView)));
        }
    }

    private Bitmap getBitmapFromCache(String url) throws IOException {
        Bitmap bitmap;
        bitmap = mLruCache.getBitmapFromMemCache(url);
        if (bitmap!=null){
            return bitmap;
        }
        return bitmap;
    }

}

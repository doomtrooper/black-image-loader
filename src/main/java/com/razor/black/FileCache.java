package com.razor.black;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by razor on 15/12/15.
 */
public class FileCache {

    public static final String TAG = FileCache.class.getSimpleName();
    private static final int GIGABYTE = 1024;
    private static final int MEGABIT = 8;

    private static FileCache singleton = null;

    // Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
    final int maxMemory;
    // Use 1/8th of the available memory for this memory cache.
    final int cacheSize;
    private static LruCache<String, Bitmap> mMemoryCache;

    private FileCache() {
        maxMemory = (int) (Runtime.getRuntime().maxMemory() / GIGABYTE);
        cacheSize = maxMemory / MEGABIT;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / GIGABYTE;
            }
        };
    }

    public static FileCache init(){
        if (singleton==null){
            synchronized (FileCache.class){
                if (singleton==null){
                    singleton = new FileCache();
                }
            }
        }
        return singleton;
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}

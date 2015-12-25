package com.razor.black;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by razor on 15/12/15.
 */
public class DiskCache {
    public static final String TAG = DiskCache.class.getSimpleName();
    private static final String FILE_EXTENTION = ".png";

    private static DiskCache mDiskCache;

    private static File mCacheDir;
    private static final String DISK_CACHE_SUBDIR = "thumbnails";


    public static DiskCache init(Context context){
        mCacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR);
        if (mCacheDir.exists()){
            mCacheDir.mkdirs();
        }
        return new DiskCache();
    }

    public void putBitmap(String key, Bitmap bitmap) throws IOException {
        File file = new File(mCacheDir, key + FILE_EXTENTION);
        FileOutputStream fOut = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
        fOut.flush();
        fOut.close();
    }

    public Bitmap getBitmap(String key) throws IOException {
        File[] files = mCacheDir.listFiles();
        if (files==null)
            return null;
        for (File file:files){
            if (file.getName().equals(key)){
                Utilities.returnBitmapFromFile(file);
            }
        }
        return null;
    }


    public void clear(){
        File[] files = mCacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }


    // Creates a unique subdirectory of the designated app cache directory. Tries to use external
    // but if not mounted, falls back on internal storage.
    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        final String cachePath = isExternalStorageWritable() || !isExternalStorageRemovable()
                            ? getExternalCacheDir(context).getPath() : context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    private static File getExternalCacheDir(Context context) {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    }

    private static boolean isExternalStorageRemovable() {
        return Environment.isExternalStorageRemovable();
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


}

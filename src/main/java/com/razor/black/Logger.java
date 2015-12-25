package com.razor.black;

import android.util.Log;

/**
 * Created by razor on 28/11/15.
 */
public class Logger {

    public static void d(String TAG, String message){
        if (Black.getInstance().isLogEnabled()){
            Log.d(TAG,message);
        }
    }

    public static void v(String TAG, String message){
        if (Black.getInstance().isLogEnabled()){
            Log.v(TAG, message);
        }
    }
    public static void e(String TAG, String message){
        if (Black.getInstance().isLogEnabled()){
            Log.e(TAG, message);
        }
    }

    public static void w(String TAG, String message){
        if (Black.getInstance().isLogEnabled()){
            Log.w(TAG, message);
        }
    }

    public static void i(String TAG, String message){
        if (Black.getInstance().isLogEnabled()){
            Log.i(TAG, message);
        }
    }

    public static void wtf(String TAG, String message){
        if (Black.getInstance().isLogEnabled()){
            Log.wtf(TAG, message);
        }
    }

}

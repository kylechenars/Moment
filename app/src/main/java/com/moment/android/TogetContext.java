package com.moment.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class TogetContext extends Application{
    private static Context context;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate() {
        //获取Context
        context = getApplicationContext();
    }

    //返回
    public static Context getContext(){
        return context;
    }
}

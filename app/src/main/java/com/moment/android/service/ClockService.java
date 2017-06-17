package com.moment.android.service;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.moment.android.Clock;
import com.moment.android.R;

public class ClockService extends Service {
    private TeBinder mBinder=new TeBinder();

    public class TeBinder extends Binder{

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(){
        super.onCreate();
        Intent intent=new Intent(this, Clock.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new Notification.Builder(this)
                .setContentTitle("休息时间到!")
                .setContentText("休息一下再回来吧！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.i_s_tee)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.i_l_tee))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        return  super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public ClockService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
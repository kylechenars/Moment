package com.moment.android;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.moment.android.Vibrator.VibratorUtil;
import com.moment.android.db.ClockDB;
import com.moment.android.service.ClockService;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Clock extends AppCompatActivity {
    Chronometer ch;
    Button start;
    Button stop;
    Button clean;

    String Fruitname;
    public String Tital;
    TextView TextTial;
    public int Worktime;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock);
        Intent intent=getIntent();
        Fruitname=intent.getStringExtra("FruitName");
        //toolbar
        Toolbar toolbar=(Toolbar)findViewById(R.id.clock_toolbar);
        setSupportActionBar(toolbar);
        //各种控件
        ch=(Chronometer)findViewById(R.id.timerCh);
        start=(Button)findViewById(R.id.startT);
        stop=(Button)findViewById(R.id.stopT);
        TextTial=(TextView)findViewById(R.id.ClockTital);
        //设置任务名
        TextTial.setText(getTI());
        //点击事件

        stop.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view){
                ch.stop();
                ch.setBase(SystemClock.elapsedRealtime());
                VibratorUtil.VibrateCancel(Clock.this);
                //停止服务
                Intent stopIntent=new Intent(Clock.this,ClockService.class);
                stopService(stopIntent);
            }
        });

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ch.setBase(SystemClock.elapsedRealtime());//计时器清零
                int hour = (int) ((SystemClock.elapsedRealtime() - ch.getBase()) / 1000 / 60);
                ch.setFormat("0"+String.valueOf(hour)+":%s");
                ch.start();
            }
        });
        //计时器监听事件
        ch.setOnChronometerTickListener(
                new Chronometer.OnChronometerTickListener(){
                    @Override
                    public void onChronometerTick(Chronometer ch){
                        if (SystemClock.elapsedRealtime()-ch.getBase()>60*1000*getWT()){
                            long[] prams={50,100,50,100};
                            VibratorUtil.Vibrate(Clock.this,prams,true);
                            Intent startIntent=new Intent(Clock.this,ClockService.class);
                            //启动服务
                            startService(startIntent);
                            ch.stop();
                        }
                    }
                });
    }


    public String getTI(){
        List<ClockDB> clockDBss=DataSupport
                .select("EventName")
                .where("FruitName=?",Fruitname)
                .find(ClockDB.class);
        for (ClockDB clockDB:clockDBss){
            Tital=clockDB.getEventName();
        }
        return Tital;
    }

    public int getWT(){
        List<ClockDB> clockDBss=DataSupport
                .select("WorkTime")
                .where("FruitName=?",Fruitname)
                .find(ClockDB.class);
        for (ClockDB clockDB:clockDBss){
            Worktime=clockDB.getWorkTime();
        }
        return Worktime;
    }
}
package com.moment.android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.moment.android.Vibrator.VibratorUtil;
import com.moment.android.db.ClockDB;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends AppCompatActivity {
    private Chronometer timer;
    private Timer timer1;
    //private TextView textView;
    //private TimerTask timerTask;
    private TextView textTital;
    private String Tital;
    private int WorkTime;
    private int RelxTime;
    private int HowManyTimes;
    private int LRelxTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock);

        Toolbar toolbar=(Toolbar)findViewById(R.id.clock_toolbar);
        setSupportActionBar(toolbar);

        timer = (Chronometer) findViewById(R.id.timer);
        //textView = (TextView) findViewById(R.id.text);
        timer1 = new Timer();
        Intent intent=getIntent();
        Tital=intent.getStringExtra("tital_data");
        textTital=(TextView) findViewById(R.id.ClockTital);
        List<ClockDB> clockDBs=DataSupport
                .select("RelxTime","NTimes","LongRelxTime")
                .where("EventName=?",Tital)
                .find(ClockDB.class);
        for (ClockDB clockDB:clockDBs){
            //Tital=clockDB.getEventName();
            RelxTime=clockDB.getRelxTime();
            HowManyTimes=clockDB.getNTimes();
            LRelxTime=clockDB.getLongRelxTime();
        }
        textTital.setText(Tital);
        //Intent intent=getIntent();
        //从创建页得到tital，用tital查询时钟要的其他数据
        //String eventName=intent.getStringExtra("tital_data");
        /*int numberID=Integer.parseInt(intent.getStringExtra("number"));
        //从SQLite查其他数据
        List<ClockDB> Clocks= DataSupport
                .select("WorkTime","RelxTime","NTimes", "LongRelxTime")
                .where("NumberId =?", String.valueOf(numberID))
                .find(ClockDB.class);
        for (ClockDB clockDB:Clocks){
            Tital=clockDB.getEventName();
            TimeWork=clockDB.getWorkTime();
            Log.d("Clock","TimeWork is "+TimeWork);
            RelxTime=clockDB.getRelxTime();
            HowManyTimes=clockDB.getNTimes();
            LRelxTime=clockDB.getLongRelxTime();
        }
        //String WorkTime=intent.getStringExtra("time_work");
        //final int TimeWork=Integer.parseInt(WorkTime);//类型转换
        textTital.setText(Tital);*/

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                List<ClockDB> clockDBss=DataSupport
                        .select("WorkTime")
                        .where("EventName=?",Tital)
                        .find(ClockDB.class);
                for (ClockDB clockDB:clockDBss){
                    WorkTime=clockDB.getWorkTime();
                }
                if(SystemClock.elapsedRealtime()-timer.getBase()>1000*60*WorkTime)
                {
                    long[] prams={50,100,50,100};
                    VibratorUtil.Vibrate(Clock.this,prams,true);
                    timer.stop();
                }
            }
        });
    }
    public void btnClick(View view) {
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0"+String.valueOf(hour)+":%s");
        timer.start();
    }
    public void stopClick(View view) {
        timer.stop();
        VibratorUtil.VibrateCancel(Clock.this);
    }

}

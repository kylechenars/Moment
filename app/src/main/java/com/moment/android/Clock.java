package com.moment.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.moment.android.Vibrator.VibratorUtil;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends AppCompatActivity {
    private Chronometer timer;
    private Timer timer1;
    //private TextView textView;
    //private TimerTask timerTask;
    private TextView textTital;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock);
        timer = (Chronometer) findViewById(R.id.timer);
        //textView = (TextView) findViewById(R.id.text);
        timer1 = new Timer();

        textTital=(TextView) findViewById(R.id.ClockTital);
        Intent intent=getIntent();
        String eventName=intent.getStringExtra("tital_data");
        String WorkTime=intent.getStringExtra("time_work");
        final int TimeWork=Integer.parseInt(WorkTime);//类型转换
        textTital.setText(eventName);

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime()-timer.getBase()>1000*60*TimeWork)
                {
                    long[] prams={50,100,50,100};
                    VibratorUtil.Vibrate(Clock.this,prams,true);
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

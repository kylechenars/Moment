package com.moment.android;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppsActivity extends AppCompatActivity{
    private List<Apps> appsList=new ArrayList<>();
    private String appsname[]=new String[10];
    private long apptime[]=new long[10];

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usage_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.usage_toolbar);
        setSupportActionBar(toolbar);

        //获取权限
        if (UStats.getUsageStatsList(this).isEmpty()){
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }
        //加载数据
        initApps(UStats.getUsageStatsList(this));
        RecyclerView recyclerview=(RecyclerView)findViewById(R.id.usage_view);
        LinearLayoutManager layoutM=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutM);
        AppsAdapter appsAdapter=new AppsAdapter(appsList);
        recyclerview.setAdapter(appsAdapter);

        AlertDialog.Builder dialog=new AlertDialog.Builder(AppsActivity.this);
        dialog.setTitle("您使用时间最长的5个应用及时间：");
        dialog.setMessage("应用名："+appsname[0]+"秒数："+String.valueOf(apptime[0])+"s"+
                "应用名："+appsname[1]+"秒数："+String.valueOf(apptime[1])+"s"+
                "应用名："+appsname[2]+"秒数："+String.valueOf(apptime[2])+"s"+
                "应用名："+appsname[3]+"秒数："+String.valueOf(apptime[3])+"s"+
                "应用名："+appsname[4]+"秒数："+String.valueOf(apptime[4]));
        dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
            }
        });
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initApps(List<UsageStats> usageStatsList) {
        //Log.d("AppsActivity:+", String.valueOf(usageStatsList.size()));
        String name[]=new String[30];
        this.appsname=name;
        long times[]=new long[30];
        this.apptime=times;
        int i=0;
        long t=0;
        String tname;
        for (UsageStats u : usageStatsList){
            Apps apps=new Apps(u.getPackageName(),String.valueOf(u.getTotalTimeInForeground()/1000));
            appsList.add(apps);
        }
        for (UsageStats u : usageStatsList){
            if (i<10) {
                if (u.getTotalTimeInForeground() != 0) {
                    name[i] = u.getPackageName();
                    times[i] = u.getTotalTimeInForeground();
                    i++;
                }
            }
            else if (i==29 && u.getTotalTimeInForeground()!=0){
                for (int j=0;j<30;j++){
                    if (times[j+1]>times[j]){
                        t=times[j+1];
                        times[j+1]=times[j];
                        times[j]=t;
                        tname=name[j+1];
                        name[j+1]=name[j];
                        name[j]=tname;
                    }
                }
            }

        }
    }
}

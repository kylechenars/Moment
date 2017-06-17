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

import com.moment.android.db.AppUsageData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppsActivity extends AppCompatActivity{
    private List<Apps> appsList=new ArrayList<>();
    private String name[]=new String[30];
    private long times[]=new long[30];

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

        /*AlertDialog.Builder dialog=new AlertDialog.Builder(AppsActivity.this);
        dialog.setTitle("您使用时间最长的5个应用及时间：");
        dialog.setMessage("应用名："+name[0]+"秒数："+String.valueOf(times[0]/1000)+"s"+
                "应用名："+name[1]+"秒数："+String.valueOf(times[1]/1000)+"s"+
                "应用名："+name[2]+"秒数："+String.valueOf(times[2]/1000)+"s"+
                "应用名："+name[3]+"秒数："+String.valueOf(times[3]/1000)+"s"+
                "应用名："+name[4]+"秒数："+String.valueOf(times[4]/1000)+"s");
        dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
            }
        });
        dialog.show();*/
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initApps(List<UsageStats> usageStatsList) {
        //Log.d("AppsActivity:+", String.valueOf(usageStatsList.size()));
        int i=0;
        long t=0;
        String tname;
        for (UsageStats u : usageStatsList){
            Apps apps=new Apps(u.getPackageName(),String.valueOf(u.getTotalTimeInForeground()/1000));
            appsList.add(apps);
        }
        /*for (UsageStats u : usageStatsList){
            //if (i<=29) {
                if (u.getTotalTimeInForeground()!=0) {
                    name[i] = u.getPackageName();
                    times[i] = u.getTotalTimeInForeground();
                    i++;
                }
            //}
            //else if (i==30){

            //}
        }
        for (int j=29;j>0;--j){
            for(int k=0;k<j;++k){
                if (times[k+1]<times[k]){
                    t=times[k];
                    times[k]=times[k+1];
                    times[k+1]=t;
                    tname=name[k];
                    name[k]=name[k+1];
                    name[k+1]=tname;
                }
            }
        }*/
    }
}
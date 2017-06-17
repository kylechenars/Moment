package com.moment.android;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.help_toolbar);
        setSupportActionBar(toolbar);

        TextView Help=(TextView)findViewById(R.id.help_view);
        Help.setText("你好，欢迎使用Moment。\n" +
                "Moment有两个主要功能：1.查看您手机上的App的使用时间。在屏幕的右上角有一个眼睛图标（长按会出现UsageEye字样），点击它会显示您使用时长排在前五的应用名及时间。2.番茄时钟。点击水果图就可以新建番茄时钟了，右上角的删除按钮会删掉您已经建立过的所有时钟。如果您还不知道番茄工作法，下面为您介绍：1、每天开始的时候规划今天要完成的几项任务，将任务逐项写在列表里（或记在软件的清单里）2、设定你的番茄钟（定时器、软件、闹钟等），时间是25分钟。3、开始完成第一项任务，直到番茄钟响铃或提醒（25分钟到）。4、停止工作，并在列表里该项任务后画个X。5、休息3~5分钟，活动、喝水、方便等等。6、开始下一个番茄钟，继续该任务。一直循环下去，直到完成该任务，并在列表里将该任务划掉。\n" +
                "当然了，您可以在新建时钟时自己决定您的工作时长。一旦到达这个时间，手机将会震动来提示您休息，按下停止按钮即可停止手机振动。\n" +
                "到此，Moment工具的使用方法就介绍完了。");
    }
}
package com.moment.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

import com.moment.android.db.ClockDB;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private DrawerLayout mDrawerLayout;
    //水果卡片载入
    private Card[] cards={new Card("Apple",R.drawable.apple),
            new Card("Banana",R.drawable.banana),
            new Card("Orange",R.drawable.orange),
            new Card("Pear",R.drawable.pear),
            new Card("Grape",R.drawable.grape),
            new Card("Pineapple",R.drawable.pineapple),
            new Card("Strawberry",R.drawable.strawberry),
            new Card("Cherry",R.drawable.cherry),
            new Card("Mango",R.drawable.mango)};

    private List<Card> cardList=new ArrayList<>();

    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //默认初始化
        Bmob.initialize(this, "7e3eeb2fefe56c1b7f4f043175339fef");
        Bmob.initialize(this, "7e3eeb2fefe56c1b7f4f043175339fef","bmob");
        //设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        .setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);
        //创建本地数据库
        Connector.getDatabase();
        //加载卡片
        initCards();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        //每一行有两个卡片;
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(cardList);
        recyclerView.setAdapter(adapter);

        //加载Toolbar
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Fab按钮显示使用说明
        //获取Fab按钮
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        //fab的监听事件
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentH=new Intent(MainActivity.this,HelpActivity.class);
                startActivity(intentH);
            }
        });
    }

    private void initCards() {
        //清空fruitList里的数据
        //cardList.clear();
        //将9种水果卡片载入
        for (int i=0;i<=8;i++)
        cardList.add(cards[i]);

    }
    //加载toolbar.xml
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    //添加toolbar上的按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //toolbar右侧menu
            case R.id.settings:
                Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
                break;
            case R.id.usageEye:
                Intent intentToUsage=new Intent(MainActivity.this,AppsActivity.class);
                startActivity(intentToUsage);
                break;
            case R.id.delete_DB:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("注意！");
                dialog.setMessage("点击确定会删除所有记录！！！");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        DataSupport.deleteAll(ClockDB.class);
                        Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                    }
                });
                dialog.show();
                break;
            case R.id.person:
                Intent intentToLogin=new Intent(MainActivity.this,Login.class);
                startActivity(intentToLogin);
                break;
            default:break;
        }
        return true;
    }
}
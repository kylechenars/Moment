package com.moment.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Connector.getDatabase();//创建数据库
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
                //Toast.makeText(MainActivity.this,"FAB",Toast.LENGTH_SHORT).show();
                //Intent intentToNewClock=new Intent(MainActivity.this,NewClock.class);
                //startActivity(intentToNewClock);
            }
        });
        //侧边菜单
        //mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        //获取Navigation实例
        //NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            //actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //菜单选项监听器
        /*navView.setNavigationItemSelectedListener(new
                NavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });*/
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
            //侧边栏
            /*case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;*/
            //toolbar右侧menu
            case R.id.settings:
                Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
                break;
            case R.id.usageEye:
                Intent intentToUsage=new Intent(MainActivity.this,AppsActivity.class);
                startActivity(intentToUsage);
            default:break;
        }
        return true;
    }
}

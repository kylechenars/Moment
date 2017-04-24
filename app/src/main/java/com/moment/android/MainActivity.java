package com.moment.android;

import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
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

        initCards();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);//每一行有两个卡片;
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(cardList);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"FAB",Toast.LENGTH_SHORT).show();
            }
        });
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new
                NavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void initCards() {
        cardList.clear();//清空fruitList里的数据
        //业务逻辑：每次新建一个番茄时钟，出现一个卡片。
        /*for (int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(cards.length);

        }*/
        cardList.add(cards[1]);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
        return true;
    }
}

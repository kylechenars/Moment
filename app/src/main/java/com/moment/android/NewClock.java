package com.moment.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moment.android.db.ClockDB;

//新建一个番茄时钟
public class NewClock extends AppCompatActivity implements View.OnClickListener{

    private EditText editName;
    private EditText editWork;

    public String inputTital;
    public String WorkTime;

    public String FruitName;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_new);
        //从上个活动获得FruitName
        Intent intent1=getIntent();
        FruitName=intent1.getStringExtra("FruitName");
        //toolbar加载
        Toolbar toolbar=(Toolbar)findViewById(R.id.clocknew_toolbar);
        setSupportActionBar(toolbar);
        //返回箭头toolbar上的
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //各种实例
        editName=(EditText)findViewById(R.id.editNameOfEvent);
        editWork=(EditText)findViewById(R.id.editWorkRange);
        Button button=(Button)findViewById(R.id.buttonMakeNew);
        //button监听事件
        button.setOnClickListener(this);
    }

    //HomeAsUp点击事件，返回上一个活动
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getTitalWorkTime(){
        inputTital = editName.getText().toString();
        WorkTime = editWork.getText().toString();
    }

    public void putToDB(){
        ClockDB clockDB = new ClockDB();
        clockDB.setFruitName(FruitName);
        clockDB.setEventName(inputTital);
        clockDB.setWorkTime(Integer.parseInt(WorkTime));
        clockDB.setReady(true);
        clockDB.setKey(1);
        clockDB.save();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMakeNew:
                getTitalWorkTime();
                if (TextUtils.isEmpty(inputTital)||TextUtils.isEmpty(WorkTime)){
                    Toast.makeText(this,"请填写完整信息！",Toast.LENGTH_SHORT).show();
                }else {
                    putToDB();
                    Intent intent = new Intent(NewClock.this, Clock.class);
                    intent.putExtra("FruitName", FruitName);;
                    startActivity(intent);
                }
                break;
            default:break;
        }
    }
}
